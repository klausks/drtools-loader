package drtools.loader.adapter.out.json.smell;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.adapter.out.json.JsonInputParser;
import drtools.loader.application.port.out.input.InputSmellConfigProvider;
import drtools.loader.application.port.out.input.InputSmellProvider;
import drtools.loader.domain.criteria.QualityAttributeName;
import drtools.loader.domain.smell.QualityAttribute;
import drtools.loader.domain.smell.Smell;
import drtools.loader.domain.smell.config.SmellConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SmellConfigMapper implements InputSmellProvider, InputSmellConfigProvider {

    private static final String DEFAULT_SMELLS_CONFIG_FILE = "drtools-smells-default.json";
    private static final String USED_SMELLS_CONFIG_FILE = "drtools-smells-default.json";

    @Value("${drtools.analysis.files.smells.dir}")
    private String SMELLS_DIR;

    private final JsonInputParser smellConfigJsonParser;
    private final Map<String, Smell> smells = new HashMap<>();
    private final Map<String, SmellConfig> smellConfigs = new HashMap<>();

    private boolean isLoaded = false;

    public SmellConfigMapper(JsonInputParser smellConfigJsonParser) {
        this.smellConfigJsonParser = smellConfigJsonParser;
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
            List<SmellConfigJsonDto> smellDefaultConfigJsons = smellConfigJsonParser.parseInput(SMELLS_DIR + File.separator + DEFAULT_SMELLS_CONFIG_FILE);
            List<SmellConfigJsonDto> smellUsedConfigJsons = smellConfigJsonParser.parseInput(SMELLS_DIR + File.separator + USED_SMELLS_CONFIG_FILE);
            initDataWithConfigs(smellDefaultConfigJsons);
            updateDataWithConfigs(smellUsedConfigJsons);
        } catch(IOException e) {
            String errMsg = String.format("Failed to load JSON input. Cause: %s", e.getMessage());
            throw new InputLoadingException(errMsg);
        }
    }

    private void initDataWithConfigs(List<SmellConfigJsonDto> configJsons) {
        configJsons.forEach(configJson -> {
            Smell smell = new Smell();
            smell.setName(configJson.smell());
            smell.setDescription(configJson.description());
            smell.setGranularity(configJson.granularity());
            var impactedQualityAttributes = impactedQualityAttributes(configJson);
            smell.setImpactedQualityAttributes(impactedQualityAttributes);
            smells.put(smell.getName(), smell);

            var smellConfig = new SmellConfig();
            smellConfig.setSmell(smell);
            smellConfig.setDefaultImportance(configJson.importance());
            smellConfig.setDefaultIntervention(configJson.intervention());
            smellConfigs.put(smell.getName(), smellConfig);
        });
    }

    private List<QualityAttribute> impactedQualityAttributes(SmellConfigJsonDto configJson) {
        return Arrays.stream(configJson.quality().split(", ")).map(qualityAttributeName -> {
            var qualityAttribute = new QualityAttribute();
            qualityAttribute.setName(QualityAttributeName.valueOf(qualityAttributeName));
            return qualityAttribute;
        }).toList();
    }

    private void updateDataWithConfigs(List<SmellConfigJsonDto> configJsons) {
        configJsons.forEach(configJson -> {
           var smellConfig = smellConfigs.get(configJson.smell());
           smellConfig.setUsedImportance(configJson.importance());
           smellConfig.setUsedIntervention(configJson.intervention());
        });
    }
}
