package drtools.loader.application;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.application.port.out.input.InputImportanceSummaryProvider;
import drtools.loader.application.port.out.input.InputInterventionSummariesProvider;
import drtools.loader.application.port.out.input.InputQualityAttributeSummariesProvider;
import drtools.loader.application.port.out.repository.ranking.ImportanceSummaryRepository;
import drtools.loader.application.port.out.repository.ranking.InterventionSummaryRepository;
import drtools.loader.application.port.out.repository.ranking.QualityAttributeSummaryRepository;
import drtools.loader.application.port.out.repository.ranking.RankingSummaryRepository;
import drtools.loader.domain.Execution;
import drtools.loader.domain.Granularity;
import drtools.loader.domain.summary.ImportanceSummary;
import drtools.loader.domain.summary.InterventionSummary;
import drtools.loader.domain.summary.QualityAttributesSummary;
import drtools.loader.domain.summary.RankingSummary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RankingSummaryLoader {
    private final InputImportanceSummaryProvider inputImportanceSummaryProvider;
    private final InputQualityAttributeSummariesProvider inputQualityAttributesSummaryProvider;
    private final InputInterventionSummariesProvider inputInterventionSummaryProvider;

    private final InterventionSummaryRepository interventionSummaryRepository;
    private final ImportanceSummaryRepository importanceSummaryRepository;
    private final QualityAttributeSummaryRepository qualityAttributesSummaryRepository;
    private final RankingSummaryRepository rankingSummaryRepository;

    public RankingSummaryLoader(
            InputImportanceSummaryProvider inputImportanceSummaryProvider,
            InputQualityAttributeSummariesProvider inputQualityAttributesSummaryProvider,
            InputInterventionSummariesProvider inputInterventionSummaryProvider,
            InterventionSummaryRepository interventionSummaryRepository,
            ImportanceSummaryRepository importanceSummaryRepository,
            QualityAttributeSummaryRepository qualityAttributesSummaryRepository, RankingSummaryRepository rankingSummaryRepository
    ) {
        this.inputImportanceSummaryProvider = inputImportanceSummaryProvider;
        this.inputQualityAttributesSummaryProvider = inputQualityAttributesSummaryProvider;
        this.inputInterventionSummaryProvider = inputInterventionSummaryProvider;
        this.interventionSummaryRepository = interventionSummaryRepository;
        this.importanceSummaryRepository = importanceSummaryRepository;
        this.qualityAttributesSummaryRepository = qualityAttributesSummaryRepository;
        this.rankingSummaryRepository = rankingSummaryRepository;
    }


    public void load(Execution execution) throws InputLoadingException {
        loadRankingSummaries(execution);
    }

    private void loadRankingSummaries(Execution execution) throws InputLoadingException {
        var savedImportanceSummaries = loadImportanceSummaries(execution);
        var savedInterventionSummaries = loadInterventionSummaries(execution);
        var savedQualityAttributesSummaries = loadQualityAttributeSummaries(execution);

        Map<Granularity, RankingSummary> rankingSummaries = new HashMap<>();
        savedQualityAttributesSummaries.forEach(qualityAttributesSummary -> {
            var rankingSummary = rankingSummaries.get(qualityAttributesSummary.getGranularity());
            if (rankingSummary == null) {
                rankingSummary = new RankingSummary();
                rankingSummaries.put(rankingSummary.getGranularity(), rankingSummary);
            }
            rankingSummary.setQualityAttributesSummary(qualityAttributesSummary);
        });

        savedImportanceSummaries.forEach(importanceSummary -> {
            var rankingSummary = rankingSummaries.get(importanceSummary.getGranularity());
            if (rankingSummary == null) {
                rankingSummary = new RankingSummary();
                rankingSummaries.put(rankingSummary.getGranularity(), rankingSummary);
            }
            rankingSummary.setImportanceSummary(importanceSummary);
        });

        savedInterventionSummaries.forEach(interventionSummary -> {
            var rankingSummary = rankingSummaries.get(interventionSummary.getGranularity());
            if (rankingSummary == null) {
                rankingSummary = new RankingSummary();
                rankingSummaries.put(rankingSummary.getGranularity(), rankingSummary);
            }
            rankingSummary.setInterventionSummary(interventionSummary);
        });
        rankingSummaryRepository.saveAll(rankingSummaries.values());
    }

    private List<QualityAttributesSummary> loadQualityAttributeSummaries(Execution execution) throws InputLoadingException {
        var inputSummaries = inputQualityAttributesSummaryProvider.getQualityAttributeSummaries();
        inputSummaries.forEach(qualityAttributesSummary -> qualityAttributesSummary.setExecution(execution));
        return qualityAttributesSummaryRepository.saveAll(inputSummaries);
    }

    private List<InterventionSummary> loadInterventionSummaries(Execution execution) throws InputLoadingException {
        var inputSummaries = inputInterventionSummaryProvider.getInterventionSummaries();
        inputSummaries.forEach(interventionSummary -> interventionSummary.setExecution(execution));
        return interventionSummaryRepository.saveAll(inputSummaries);
    }

    private List<ImportanceSummary> loadImportanceSummaries(Execution execution) throws InputLoadingException {
        var inputSummaries = inputImportanceSummaryProvider.getImportanceSummaries();
        inputSummaries.forEach(importanceSummary -> importanceSummary.setExecution(execution));
        return importanceSummaryRepository.saveAll(inputSummaries);
    }
}
