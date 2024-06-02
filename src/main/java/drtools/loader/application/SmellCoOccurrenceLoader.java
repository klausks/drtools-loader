package drtools.loader.application;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.application.port.out.input.InputCoOccurrenceQualityImpactProvider;
import drtools.loader.application.port.out.input.InputSmellCoOccurrenceProvider;
import drtools.loader.application.port.out.input.InputSmellCoOccurrenceSummaryProvider;
import drtools.loader.application.port.out.repository.smell.CoOccurrenceQualityImpactRepository;
import drtools.loader.application.port.out.repository.smell.SmellCoOccurrenceRepository;
import drtools.loader.application.port.out.repository.smell.SmellCoOccurrenceSummaryRepository;
import drtools.loader.application.service.CoOccurrenceQualityImpactDeltaService;
import drtools.loader.application.service.SmellCoOccurrenceDeltaService;
import drtools.loader.domain.Execution;
import drtools.loader.domain.smell.SmellCoOccurrence;
import drtools.loader.domain.summary.SmellCoOccurrenceSummary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SmellCoOccurrenceLoader {
    private final InputCoOccurrenceQualityImpactProvider inputCoOccurrenceQualityImpactProvider;
    private final InputSmellCoOccurrenceProvider inputSmellCoOccurrenceProvider;
    private final InputSmellCoOccurrenceSummaryProvider inputSmellCoOccurrenceSummaryProvider;

    private final CoOccurrenceQualityImpactRepository coOccurrenceQualityImpactRepository;
    private final SmellCoOccurrenceSummaryRepository smellCoOccurrenceSummaryRepository;
    private final SmellCoOccurrenceRepository smellCoOccurrenceRepository;

    private final SmellCoOccurrenceDeltaService smellCoOccurrenceDeltaService;
    private final CoOccurrenceQualityImpactDeltaService coOccurrenceQualityImpactDeltaService;

    public SmellCoOccurrenceLoader(
            InputCoOccurrenceQualityImpactProvider inputCoOccurrenceQualityImpactProvider,
            InputSmellCoOccurrenceProvider inputSmellCoOccurrenceProvider,
            InputSmellCoOccurrenceSummaryProvider inputSmellCoOccurrenceSummaryProvider,
            CoOccurrenceQualityImpactRepository coOccurrenceQualityImpactRepository,
            SmellCoOccurrenceSummaryRepository smellCoOccurrenceSummaryRepository,
            SmellCoOccurrenceDeltaService smellCoOccurrenceDeltaService,
            CoOccurrenceQualityImpactDeltaService coOccurrenceQualityImpactDeltaService,
            SmellCoOccurrenceRepository smellCoOccurrenceRepository
    ) {
        this.inputCoOccurrenceQualityImpactProvider = inputCoOccurrenceQualityImpactProvider;
        this.inputSmellCoOccurrenceProvider = inputSmellCoOccurrenceProvider;
        this.inputSmellCoOccurrenceSummaryProvider = inputSmellCoOccurrenceSummaryProvider;
        this.coOccurrenceQualityImpactRepository = coOccurrenceQualityImpactRepository;
        this.smellCoOccurrenceSummaryRepository = smellCoOccurrenceSummaryRepository;
        this.smellCoOccurrenceDeltaService = smellCoOccurrenceDeltaService;
        this.coOccurrenceQualityImpactDeltaService = coOccurrenceQualityImpactDeltaService;
        this.smellCoOccurrenceRepository = smellCoOccurrenceRepository;
    }

    public void load(Execution execution) throws InputLoadingException {
        loadCoOccurrenceQualityImpacts();
        loadSmellCoOccurrences(execution);
        loadSummary(execution);
    }

    private void loadSummary(Execution execution) throws InputLoadingException {
        var input = inputSmellCoOccurrenceSummaryProvider.getSmellCoOccurrenceSummaries();
        linkDependencies(input, execution);
        smellCoOccurrenceSummaryRepository.saveAll(input);
    }

    private void loadSmellCoOccurrences(Execution execution) throws InputLoadingException {
        var input = inputSmellCoOccurrenceProvider.getSmellCoOccurrences();
        if (execution.getId() == 1) {
            smellCoOccurrenceRepository.saveAll(input);
        } else {
            smellCoOccurrenceRepository.saveAll(smellCoOccurrenceDeltaService.getChanges(input));
        }
    }

    private void linkDependencies(List<SmellCoOccurrenceSummary> records, Execution execution) {
        var coOccurrenceRecords = smellCoOccurrenceRepository.findAll().stream().collect(Collectors.toMap(SmellCoOccurrence::getDescription, Function.identity()));
        records.forEach(smellCoOccurrenceSummary ->  {
            smellCoOccurrenceSummary.setCoOccurrence(coOccurrenceRecords.get(smellCoOccurrenceSummary.getCoOccurrence().getDescription()));
            smellCoOccurrenceSummary.setExecution(execution);
        });
    }

    private void loadCoOccurrenceQualityImpacts() throws InputLoadingException {
        var input = inputCoOccurrenceQualityImpactProvider.getCoOccurrenceQualityImpacts();
       coOccurrenceQualityImpactRepository.saveAll(coOccurrenceQualityImpactDeltaService.getChanges(input));
    }
}
