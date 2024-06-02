package drtools.loader.adapter.out.json.smell.criteria;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.application.port.out.input.InputImportanceConfigsProvider;
import drtools.loader.application.port.out.input.InputInterventionConfigsProvider;
import drtools.loader.application.port.out.input.InputQualityAttributesConfigProvider;
import drtools.loader.application.port.out.input.InputQualityAttributesProvider;
import drtools.loader.domain.criteria.Criteria;
import drtools.loader.domain.criteria.Importance;
import drtools.loader.domain.criteria.Intervention;
import drtools.loader.domain.criteria.QualityAttributeName;
import drtools.loader.domain.smell.QualityAttribute;
import drtools.loader.domain.smell.config.ImportanceConfig;
import drtools.loader.domain.smell.config.InterventionConfig;
import drtools.loader.domain.smell.config.QualityAttributeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CriteriaMapper implements InputQualityAttributesProvider, InputQualityAttributesConfigProvider, InputInterventionConfigsProvider, InputImportanceConfigsProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CriteriaMapper.class);

    private final CriteriaConfigJsonParser criteriaConfigJsonParser;
    private final Map<QualityAttributeName, QualityAttribute> qualityAttributes = new HashMap<>();
    private final Map<Importance, ImportanceConfig> importanceConfigs = new HashMap<>();
    private final Map<Intervention, InterventionConfig> interventionConfigs = new HashMap<>();
    private final Map<QualityAttributeName, QualityAttributeConfig> qualityAttributesConfigs = new HashMap<>();

    private boolean isDataLoaded = false;

    public CriteriaMapper(CriteriaConfigJsonParser criteriaConfigJsonParser) {
        this.criteriaConfigJsonParser = criteriaConfigJsonParser;
    }

    @Override
    public Map<QualityAttributeName, QualityAttribute> getInputQualityAttributes() throws InputLoadingException {
        ensureLoaded();
        return qualityAttributes;
    }

    @Override
    public Map<QualityAttributeName, QualityAttributeConfig> getInputQualityAttributesConfigs() throws InputLoadingException {
        ensureLoaded();
        return qualityAttributesConfigs;
    }

    @Override
    public Map<Importance, ImportanceConfig> getInputImportanceConfigs() throws InputLoadingException {
        ensureLoaded();
        return importanceConfigs;
    }

    @Override
    public Map<Intervention, InterventionConfig> getInputInterventionConfigs() throws InputLoadingException {
        ensureLoaded();
        return interventionConfigs;
    }

    private void ensureLoaded() throws InputLoadingException {
        if (!isDataLoaded) {
            loadInputData();
            isDataLoaded = true;
        }
    }

    private void loadInputData() throws InputLoadingException {
        try {
            List<CriteriaConfigJson> defaultCriteriaConfigJsons = criteriaConfigJsonParser.parseDefault();
            List<CriteriaConfigJson> usedCriteriaConfigJsons = criteriaConfigJsonParser.parseDefault();
            initDataWithDefaultConfigs(defaultCriteriaConfigJsons);
            updateDataWithUsedConfigs(usedCriteriaConfigJsons);
        } catch(IOException e) {
            String errMsg = String.format("Failed to load JSON input. Cause: %s", e.getMessage());
            throw new InputLoadingException(errMsg);
        }
    }

    private void updateDataWithUsedConfigs(List<CriteriaConfigJson> usedCriteriaConfigs) {
        for (CriteriaConfigJson criteriaConfigJson : usedCriteriaConfigs) {
            Criteria criteria = criteriaConfigJson.criteria();
            var attributes = criteriaConfigJson.data();
            switch(criteria) {
                case INTERVENTION -> attributes.forEach(attr -> {
                    var intervention = Intervention.valueOf(attr.description().toUpperCase());
                    interventionConfigs.get(intervention).setWeightUsed(attr.weight());
                });
                case QUALITY -> attributes.forEach(attr -> {
                    var qualityAttribute = QualityAttributeName.valueOf(attr.description().toUpperCase());
                    qualityAttributesConfigs.get(qualityAttribute).setImpactUsed(attr.impact());
                    qualityAttributesConfigs.get(qualityAttribute).setWeightUsed(attr.weight());
                } );
                case IMPORTANCE -> attributes.forEach(attr -> {
                    var importance = Importance.valueOf(attr.description().toUpperCase());
                    importanceConfigs.get(importance).setWeightUsed(attr.weight());
                });
            }
        }
    }

    private void initDataWithDefaultConfigs(List<CriteriaConfigJson> defaultCriteriaConfigs) {
        for (CriteriaConfigJson criteriaConfigJson : defaultCriteriaConfigs) {
            Criteria criteria = criteriaConfigJson.criteria();
            var attributes = criteriaConfigJson.data();
            switch(criteria) {
                case INTERVENTION -> attributes.forEach(attr -> {
                    var intervention = Intervention.valueOf(attr.description().toUpperCase());
                    var interventionConfig = new InterventionConfig();
                    interventionConfig.setIntervention(intervention);
                    interventionConfig.setWeightDefault(attr.weight());
                    interventionConfigs.put(intervention, interventionConfig);
                } );
                case QUALITY -> attributes.forEach(attr -> {
                    var qualityAttributeName = QualityAttributeName.valueOf(attr.description().toUpperCase());
                    var qualityAttribute = new QualityAttribute();
                    qualityAttribute.setName(qualityAttributeName);
                    qualityAttributes.put(qualityAttribute.getName(), qualityAttribute);
                    var qualityAttributesConfig = new QualityAttributeConfig();
                    qualityAttributesConfig.setQualityAttribute(qualityAttribute);
                    qualityAttributesConfig.setWeightDefault(attr.weight());
                    qualityAttributesConfig.setImpactDefault(attr.impact());
                    qualityAttributesConfigs.put(qualityAttributeName, qualityAttributesConfig);
                } );
                case IMPORTANCE -> attributes.forEach(attr -> {
                    var importance = Importance.valueOf(attr.description().toUpperCase());
                    var importanceConfig = new ImportanceConfig();
                    importanceConfig.setImportance(importance);
                    importanceConfig.setWeightDefault(attr.weight());
                    importanceConfigs.put(importance, importanceConfig);
                } );
            }
        }
    }
}
