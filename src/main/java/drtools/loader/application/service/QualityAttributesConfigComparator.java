package drtools.loader.application.service;

import drtools.loader.application.port.out.repository.ExecutionRepository;
import drtools.loader.application.port.out.repository.smell.config.QualityAttributeConfigRepository;
import drtools.loader.domain.criteria.QualityAttributeName;
import drtools.loader.domain.smell.config.QualityAttributeConfig;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class QualityAttributesConfigComparator {
    private final QualityAttributeConfigRepository qualityAttributeConfigRepository;
    private final ExecutionRepository executionRepository;

    public QualityAttributesConfigComparator(QualityAttributeConfigRepository qualityAttributeConfigRepository,
                                             ExecutionRepository executionRepository
    ) {
        this.qualityAttributeConfigRepository = qualityAttributeConfigRepository;
        this.executionRepository = executionRepository;
    }

    public List<QualityAttributeConfig> getChangedConfigs(Map<QualityAttributeName, QualityAttributeConfig> inputConfigs) {
        List<QualityAttributeConfig> changedConfigs = new ArrayList<>();
        int lastCompletedExecutionId = executionRepository.findLastCompletedExecutionId();
        var currentConfigs = qualityAttributeConfigRepository.findByExecutionId(lastCompletedExecutionId).stream().collect(
                Collectors.toMap(qualityAttributeConfig -> qualityAttributeConfig.getQualityAttribute().getName(), Function.identity()));
        currentConfigs.forEach((qualityAttributeName, currentConfig) -> {
            var newConfig = inputConfigs.get(qualityAttributeName);
            if (hasChanged(currentConfig, newConfig)) {
                changedConfigs.add(newConfig);
            }
        });
        return changedConfigs;
    }

    private boolean hasChanged(QualityAttributeConfig currentConfig, QualityAttributeConfig newConfig) {
        return !currentConfig.getQualityAttribute().getName().equals(newConfig.getQualityAttribute().getName())
                || !currentConfig.getImpactUsed().equals(newConfig.getImpactUsed())
                || !currentConfig.getImpactDefault().equals(newConfig.getImpactDefault())
                || currentConfig.getWeightDefault() != newConfig.getWeightDefault()
                || currentConfig.getWeightUsed() != newConfig.getWeightUsed();
    }

}
