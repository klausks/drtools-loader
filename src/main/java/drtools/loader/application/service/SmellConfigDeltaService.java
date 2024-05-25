package drtools.loader.application.service;

import drtools.loader.application.port.out.repository.ExecutionRepository;
import drtools.loader.application.port.out.repository.smell.config.SmellConfigRepository;
import drtools.loader.domain.smell.config.SmellConfig;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SmellConfigDeltaService {

    private final SmellConfigRepository smellConfigRepository;
    private final ExecutionRepository executionRepository;

    public SmellConfigDeltaService(SmellConfigRepository smellConfigRepository, ExecutionRepository executionRepository) {
        this.smellConfigRepository = smellConfigRepository;
        this.executionRepository = executionRepository;
    }

    public List<SmellConfig> getChangedConfigs(Map<String, SmellConfig> inputConfigs) {
        List<SmellConfig> changedConfigs = new ArrayList<>();
        int lastCompletedExecutionId = executionRepository.findLastCompletedExecutionId();
        var currentConfigs = smellConfigRepository.findByExecutionId(lastCompletedExecutionId).stream().collect(
                Collectors.toMap(smellConfig -> smellConfig.getSmell().getName(), Function.identity()));
        currentConfigs.forEach((smellName, currentConfig) -> {
            var newConfig = inputConfigs.get(smellName);
            if (hasChanged(currentConfig, newConfig)) {
                // Needs to be set due to foreign key relationship, so that when the record is saved, it references the existing quality attribute record.
                newConfig.setSmell(currentConfig.getSmell());
                changedConfigs.add(newConfig);
            }
        });
        return changedConfigs;
    }

    private boolean hasChanged(SmellConfig currentConfig, SmellConfig newConfig) {
        if (!currentConfig.getSmell().getName().equals(newConfig.getSmell().getName())) {
            throw new RuntimeException();
        }
        return !currentConfig.getDefaultImportance().equals(newConfig.getDefaultImportance())
                || !currentConfig.getUsedImportance().equals(newConfig.getUsedImportance())
                || currentConfig.getDefaultIntervention() != newConfig.getDefaultIntervention()
                || currentConfig.getUsedIntervention() != newConfig.getUsedIntervention();
    }
}
