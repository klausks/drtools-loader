package drtools.loader.application.service;

import drtools.loader.application.port.out.repository.ExecutionRepository;
import drtools.loader.application.port.out.repository.smell.config.InterventionConfigRepository;
import drtools.loader.domain.criteria.Intervention;
import drtools.loader.domain.smell.config.InterventionConfig;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class InterventionConfigDeltaService {
    private final InterventionConfigRepository interventionConfigrepository;
    private final ExecutionRepository executionRepository;

    public InterventionConfigDeltaService(InterventionConfigRepository interventionConfigrepository, ExecutionRepository executionRepository) {
        this.interventionConfigrepository = interventionConfigrepository;
        this.executionRepository = executionRepository;
    }

    public List<InterventionConfig> getChangedConfigs(Map<Intervention, InterventionConfig> inputConfigs) {
        List<InterventionConfig> changedConfigs = new ArrayList<>();
        int lastCompletedExecutionId = executionRepository.findLastCompletedExecutionId();
        var currentConfigs = interventionConfigrepository.findByExecutionId(lastCompletedExecutionId).stream().collect(
                Collectors.toMap(InterventionConfig::getIntervention, Function.identity()));
        currentConfigs.forEach((importance, currentConfig) -> {
            var newConfig = inputConfigs.get(importance);
            if (hasChanged(currentConfig, newConfig)) {
                changedConfigs.add(newConfig);
            }
        });
        return changedConfigs;
    }

    private boolean hasChanged(InterventionConfig currentConfig, InterventionConfig newConfig) {
        return !currentConfig.getIntervention().equals(newConfig.getIntervention())
                || currentConfig.getWeightDefault() != newConfig.getWeightDefault()
                || currentConfig.getWeightUsed() != newConfig.getWeightUsed();
    }
}
