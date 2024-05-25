package drtools.loader.adapter.out.json.smell;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.application.port.out.InputSmellConfigProvider;
import drtools.loader.application.port.out.InputSmellProvider;
import drtools.loader.application.port.out.repository.smell.config.QualityAttributeRepository;
import drtools.loader.domain.criteria.QualityAttributeName;
import drtools.loader.domain.smell.QualityAttribute;
import drtools.loader.domain.smell.Smell;
import drtools.loader.domain.smell.config.SmellConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SmellMapper implements InputSmellProvider, InputSmellConfigProvider {

    private final SmellConfigJsonParser smellConfigJsonParser;
    private final QualityAttributeRepository qualityAttributeRepository;
    private final Map<String, Smell> smells = new HashMap<>();
    private final Map<String, SmellConfig> smellConfigs = new HashMap<>();

    private boolean isLoaded = false;

    public SmellMapper(SmellConfigJsonParser smellConfigJsonParser, QualityAttributeRepository qualityAttributeRepository) {
        this.smellConfigJsonParser = smellConfigJsonParser;
        this.qualityAttributeRepository = qualityAttributeRepository;
    }

    @Override
    public Map<String, SmellConfig> getSmellConfigs() throws InputLoadingException {
        ensureLoaded();
        return smellConfigs;
    }

    @Override
    public Map<String, Smell> getSmells() throws InputLoadingException {
        ensureLoaded();
        return smells;
    }

    private void ensureLoaded() throws InputLoadingException {
        if (!isLoaded) {
            loadInputData();
            isLoaded = true;
        }
    }

    private void loadInputData() throws InputLoadingException {
        try {
            var smellDefaultConfigJsons = smellConfigJsonParser.parseDefault();
            var smellUsedConfigJsons = smellConfigJsonParser.parseUsed();
            initDataWithConfigs(smellDefaultConfigJsons);
            updateDataWithConfigs(smellUsedConfigJsons);
        } catch(IOException e) {
            String errMsg = String.format("Failed to load JSON input. Cause: %s", e.getMessage());
            throw new InputLoadingException(errMsg);
        }
    }

    private void initDataWithConfigs(List<SmellConfigJsonDto> configJsons) {
        var qualityAttributes = qualityAttributeRepository.findAll().stream().collect(Collectors.toMap(QualityAttribute::getName, Function.identity()));
        configJsons.forEach(configJson -> {
            Smell smell = new Smell();
            smell.setName(configJson.smell());
            smell.setDescription(configJson.description());
            smell.setGranularity(configJson.granularity());
            var impactedQualityAttributes = mapImpactedQualityAttributes(configJson, qualityAttributes);
            smell.setImpactedQualityAttributes(impactedQualityAttributes);
            smells.put(smell.getName(), smell);

            var smellConfig = new SmellConfig();
            smellConfig.setSmell(smell);
            smellConfig.setDefaultImportance(configJson.importance());
            smellConfig.setDefaultIntervention(configJson.intervention());
            smellConfigs.put(smell.getName(), smellConfig);
        });
    }

    private List<QualityAttribute> mapImpactedQualityAttributes (SmellConfigJsonDto configJson, Map<QualityAttributeName, QualityAttribute> qualityAttributes) {
        var impactedQualityAttributeNames = Arrays.asList(configJson.quality().split(", "));
        return impactedQualityAttributeNames.stream().map(
                attrName -> qualityAttributes.get(QualityAttributeName.valueOf(attrName.toUpperCase()))
        ).toList();
    }

    private void updateDataWithConfigs(List<SmellConfigJsonDto> configJsons) {
        configJsons.forEach(configJson -> {
           var smellConfig = smellConfigs.get(configJson.smell());
           smellConfig.setUsedImportance(configJson.importance());
           smellConfig.setUsedIntervention(configJson.intervention());
        });
    }
}
