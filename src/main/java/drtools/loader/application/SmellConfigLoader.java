package drtools.loader.application;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.application.port.out.InputSmellConfigProvider;
import drtools.loader.application.port.out.InputSmellProvider;
import drtools.loader.application.service.SmellConfigDeltaService;
import drtools.loader.application.port.out.repository.smell.SmellRepository;
import drtools.loader.application.port.out.repository.smell.config.SmellConfigRepository;
import drtools.loader.application.service.SmellDeltaService;
import drtools.loader.domain.Execution;
import org.springframework.stereotype.Component;

@Component
public class SmellConfigLoader {

    private final InputSmellConfigProvider inputSmellConfigProvider;
    private final InputSmellProvider inputSmellsProvider;

    private final SmellRepository smellRepository;
    private final SmellConfigRepository smellConfigRepository;

    private final SmellConfigDeltaService smellConfigDeltaService;
    private final SmellDeltaService smellDeltaService;

    public SmellConfigLoader(
            InputSmellConfigProvider smellConfigProvider,
            InputSmellProvider inputSmellsProvider,
            SmellRepository smellRepository,
            SmellConfigRepository smellConfigRepository,
            SmellConfigDeltaService smellConfigDeltaService, SmellDeltaService smellDeltaService
    ) {
        this.inputSmellConfigProvider = smellConfigProvider;
        this.inputSmellsProvider = inputSmellsProvider;
        this.smellRepository = smellRepository;
        this.smellConfigRepository = smellConfigRepository;
        this.smellConfigDeltaService = smellConfigDeltaService;
        this.smellDeltaService = smellDeltaService;
    }

    public void loadSmells() throws InputLoadingException {
        smellRepository.saveAll(smellDeltaService.getChangedSmells(inputSmellsProvider.getSmells()));
    }

    public void loadSmellConfig(Execution execution) throws InputLoadingException {
        var inputSmellConfigs = inputSmellConfigProvider.getSmellConfigs();
        var configsToBeLoaded = execution.getId() == 1 ? inputSmellConfigs.values() : smellConfigDeltaService.getChangedConfigs(inputSmellConfigs);
        smellConfigRepository.saveAll(configsToBeLoaded);
    }
}
