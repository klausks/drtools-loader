package drtools.loader.application.service;

import drtools.loader.application.port.out.repository.ExecutionRepository;
import drtools.loader.application.port.out.repository.smell.config.ImportanceConfigRepository;
import drtools.loader.domain.criteria.Importance;
import drtools.loader.domain.smell.config.ImportanceConfig;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ImportanceConfigDeltaService {
    private final ImportanceConfigRepository importanceConfigRepository;
    private final ExecutionRepository executionRepository;

    public ImportanceConfigDeltaService(ImportanceConfigRepository importanceConfigRepository, ExecutionRepository executionRepository) {
        this.importanceConfigRepository = importanceConfigRepository;
        this.executionRepository = executionRepository;
    }

    public List<ImportanceConfig> getChangedConfigs(Map<Importance, ImportanceConfig> inputConfigs) {
        List<ImportanceConfig> changedConfigs = new ArrayList<>();
        int lastCompletedExecutionId = executionRepository.findLastCompletedExecutionId();
        var currentConfigs = importanceConfigRepository.findByExecutionId(lastCompletedExecutionId).stream().collect(
                Collectors.toMap(ImportanceConfig::getImportance, Function.identity()));
        currentConfigs.forEach((importance, currentConfig) -> {
            var newConfig = inputConfigs.get(importance);
            if (hasChanged(currentConfig, newConfig)) {
                changedConfigs.add(newConfig);
            }
        });
        return changedConfigs;
    }

    private boolean hasChanged(ImportanceConfig currentConfig, ImportanceConfig newConfig) {
        return !currentConfig.getImportance().equals(newConfig.getImportance())
                || currentConfig.getWeightDefault() != newConfig.getWeightDefault()
                || currentConfig.getWeightUsed() != newConfig.getWeightUsed();
    }
}
