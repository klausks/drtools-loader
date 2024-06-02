package drtools.loader.application;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.application.port.out.input.InputSmellConfigProvider;
import drtools.loader.application.port.out.input.InputSmellProvider;
import drtools.loader.application.port.out.input.InputSmellSummaryResumesProvider;
import drtools.loader.application.port.out.input.InputSmellsSummaryProvider;
import drtools.loader.application.port.out.repository.smell.SmellSummaryResumeRepository;
import drtools.loader.application.port.out.repository.smell.SmellsSummaryRepository;
import drtools.loader.application.service.SmellConfigDeltaService;
import drtools.loader.application.port.out.repository.smell.SmellRepository;
import drtools.loader.application.port.out.repository.smell.config.SmellConfigRepository;
import drtools.loader.application.service.SmellDeltaService;
import drtools.loader.domain.Execution;
import drtools.loader.domain.smell.Smell;
import drtools.loader.domain.summary.SmellSummary;
import drtools.loader.domain.summary.SmellsSummaryResume;
import drtools.loader.utils.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmellLoader {

    private final InputSmellConfigProvider inputSmellConfigProvider;
    private final InputSmellProvider inputSmellsProvider;
    private final InputSmellsSummaryProvider inputSmellSummaryProvider;
    private final InputSmellSummaryResumesProvider inputSmellSummaryResumesProvider;

    private final SmellRepository smellRepository;
    private final SmellConfigRepository smellConfigRepository;
    private final SmellsSummaryRepository smellsSummaryRepository;
    private final SmellSummaryResumeRepository smellSummaryResumeRepository;

    private final SmellConfigDeltaService smellConfigDeltaService;
    private final SmellDeltaService smellDeltaService;

    public SmellLoader(
            InputSmellConfigProvider smellConfigProvider,
            InputSmellProvider inputSmellsProvider, InputSmellsSummaryProvider inputSmellSummaryProvider,
            InputSmellSummaryResumesProvider inputSmellSummaryResumesProvider,
            SmellRepository smellRepository,
            SmellConfigRepository smellConfigRepository,
            SmellsSummaryRepository smellsSummaryRepository,
            SmellSummaryResumeRepository smellSummaryResumeRepository,
            SmellConfigDeltaService smellConfigDeltaService, SmellDeltaService smellDeltaService
    ) {
        this.inputSmellConfigProvider = smellConfigProvider;
        this.inputSmellsProvider = inputSmellsProvider;
        this.inputSmellSummaryProvider = inputSmellSummaryProvider;
        this.inputSmellSummaryResumesProvider = inputSmellSummaryResumesProvider;
        this.smellRepository = smellRepository;
        this.smellConfigRepository = smellConfigRepository;
        this.smellsSummaryRepository = smellsSummaryRepository;
        this.smellSummaryResumeRepository = smellSummaryResumeRepository;
        this.smellConfigDeltaService = smellConfigDeltaService;
        this.smellDeltaService = smellDeltaService;
    }

    public void load(Execution execution) throws InputLoadingException {
        loadSmells();
        loadSmellConfig(execution);
        loadSmellSummaries(execution);
        loadSmellSummaryResumes(execution);
    }

    private void loadSmellSummaries(Execution execution) throws InputLoadingException {
        var inputSummaries = inputSmellSummaryProvider.getSmellsSummaries();
        this.linkSummaryReferences(inputSummaries, execution);
        smellsSummaryRepository.saveAll(inputSummaries);
    }

    private void linkSummaryReferences(List<SmellSummary> inputSummaries, Execution execution) {
        var smellNames = inputSummaries.stream().map(summary -> summary.getSmell().getName()).toList();
        var smellRecords = CollectionUtils.toMap(smellRepository.findByNameIn(smellNames), Smell::getName);
        inputSummaries.forEach(smellSummary -> {
            smellSummary.setExecution(execution);
            var smellName = smellSummary.getSmell().getName();
            smellSummary.setSmell(smellRecords.get(smellName));
        });
    }

    private void loadSmellSummaryResumes(Execution execution) throws InputLoadingException {
        var inputSummaryResumes = inputSmellSummaryResumesProvider.getInputSmellSummaryResumes();
        linkSummaryResumeReferences(inputSummaryResumes, execution);
        smellSummaryResumeRepository.saveAll(inputSummaryResumes);
    }

    private void linkSummaryResumeReferences(List<SmellsSummaryResume> inputSummaryResumes, Execution execution) {
        inputSummaryResumes.forEach(smellSummaryResume -> smellSummaryResume.setExecution(execution));
    }

    private void loadSmells() throws InputLoadingException {
        smellRepository.saveAll(smellDeltaService.getChangedSmells(inputSmellsProvider.getSmells()));
    }

    private void loadSmellConfig(Execution execution) throws InputLoadingException {
        var inputSmellConfigs = inputSmellConfigProvider.getSmellConfigs();
        var configsToBeLoaded = execution.getId() == 1 ? inputSmellConfigs.values() : smellConfigDeltaService.getChangedConfigs(inputSmellConfigs);
        smellConfigRepository.saveAll(configsToBeLoaded);
    }
}
