package drtools.loader;

import drtools.loader.in.metrics.MetricThresholdsParser;
import drtools.loader.in.smell.SmellConfigParser;
import drtools.loader.in.smell.criteria.CriteriaConfig;
import drtools.loader.in.smell.criteria.CriteriaConfigParser;
import drtools.loader.model.Execution;
import drtools.loader.model.criteria.Criteria;
import drtools.loader.model.smell.config.ImportanceConfig;
import drtools.loader.model.smell.config.InterventionConfig;
import drtools.loader.model.smell.config.QualityAttributeConfig;
import drtools.loader.model.smell.config.SmellConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ConfigLoader {
    private final MetricThresholdsParser metricThresholdsParser;
    private final SmellConfigParser smellConfigParser;
    private final CriteriaConfigParser criteriaConfigParser;


    public ConfigLoader(MetricThresholdsParser metricThresholdsParser, SmellConfigParser smellConfigParser, CriteriaConfigParser criteriaConfigParser) {
        this.metricThresholdsParser = metricThresholdsParser;
        this.smellConfigParser = smellConfigParser;
        this.criteriaConfigParser = criteriaConfigParser;
    }

    public void loadCriteriaConfig(Execution execution) throws IOException {
        List<CriteriaConfig> defaultCriteriaConfig = criteriaConfigParser.parseDefault();
        List<CriteriaConfig> usedCriteriaConfig = criteriaConfigParser.parseUsed();
        var importanceConfigs = new HashMap<String, ImportanceConfig>();
        var interventionConfigs = new HashMap<String, InterventionConfig>();
        var qualityAttributesConfigs = new HashMap<String, QualityAttributeConfig>();
        var smellConfigs = new HashMap<String, SmellConfig>();

        extractDefaultConfigs(execution, defaultCriteriaConfig, interventionConfigs, qualityAttributesConfigs, importanceConfigs);
        for (CriteriaConfig criteriaConfig : usedCriteriaConfig) {
            Criteria criteria = criteriaConfig.criteria();
            var attributes = criteriaConfig.data();
            switch(criteria) {
                case INTERVENTION -> {
                    attributes.forEach(attr -> {
                        interventionConfigs.get(attr.description()).setWeightUsed(attr.weight());
                    } );
                }
                case QUALITY -> {
                    attributes.forEach(attr -> {
                       qualityAttributesConfigs.get(attr.description()).setImpactUsed(attr.impact());
                       qualityAttributesConfigs.get(attr.description()).setWeightUsed(attr.weight());
                    } );
                }
                case IMPORTANCE -> {
                    attributes.forEach(attr -> {
                        importanceConfigs.get(attr.description()).setWeightUsed(attr.weight());
                    } );
                }
            }
        }

    }

    private static void extractDefaultConfigs(Execution execution, List<CriteriaConfig> defaultCriteriaConfig, HashMap<String, InterventionConfig> interventionConfigs, HashMap<String, QualityAttributeConfig> qualityAttributesConfigs, HashMap<String, ImportanceConfig> importanceConfigs) {
        for (CriteriaConfig criteriaConfig : defaultCriteriaConfig) {
            Criteria criteria = criteriaConfig.criteria();
            var attributes = criteriaConfig.data();
            switch(criteria) {
                case INTERVENTION -> {
                    attributes.forEach(attr -> {
                        var interventionConfig = new InterventionConfig();
                        interventionConfig.setInterventionDescription(attr.description());
                        interventionConfig.setWeightDefault(attr.weight());
                        interventionConfig.setLastExecution(execution);
                        interventionConfigs.put(attr.description(), interventionConfig);
                    } );
                }
                case QUALITY -> {
                    attributes.forEach(attr -> {
                        var qualityAttributesConfig = new QualityAttributeConfig();
                        qualityAttributesConfig.setQualityAttributeDescription(attr.description());
                        qualityAttributesConfig.setWeightDefault(attr.weight());
                        qualityAttributesConfig.setImpactDefault(attr.impact());
                        qualityAttributesConfig.setLastExecution(execution);
                        qualityAttributesConfigs.put(attr.description(), qualityAttributesConfig);
                    } );
                }
                case IMPORTANCE -> {
                    attributes.forEach(attr -> {
                        var importanceConfig = new ImportanceConfig();
                        importanceConfig.setImportanceDescription(attr.description());
                        importanceConfig.setWeightDefault(attr.weight());
                        importanceConfig.setLastExecution(execution);
                        importanceConfigs.put(attr.description(), importanceConfig);
                    } );
                }
            }
        }
    }

    private InterventionConfig toInterventionConfig(CriteriaConfig.Attributes attributes, boolean isDefault) {
        var interventionConfig = new InterventionConfig();

    }

    public void loadSmellConfig() {

    }

    public void loadMetricThresholds() {}
}
