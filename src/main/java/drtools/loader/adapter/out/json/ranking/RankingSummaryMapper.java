package drtools.loader.adapter.out.json.ranking;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.adapter.out.json.JsonInputParser;
import drtools.loader.application.port.out.input.InputImportanceSummaryProvider;
import drtools.loader.application.port.out.input.InputInterventionSummariesProvider;
import drtools.loader.application.port.out.input.InputQualityAttributeSummariesProvider;
import drtools.loader.domain.Granularity;
import drtools.loader.domain.criteria.Importance;
import drtools.loader.domain.criteria.Intervention;
import drtools.loader.domain.criteria.QualityAttributeName;
import drtools.loader.domain.summary.ImportanceSummary;
import drtools.loader.domain.summary.InterventionSummary;
import drtools.loader.domain.summary.QualityAttributesSummary;
import drtools.loader.domain.summary.RankingSummary;
import drtools.loader.utils.ParsingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RankingSummaryMapper implements InputInterventionSummariesProvider, InputImportanceSummaryProvider, InputQualityAttributeSummariesProvider {

    private static final String RANKING_SUMMARY_FILE_NAME = "drtools-summary-ranking.json";

    private final JsonInputParser jsonInputParser;

    @Value("${drtools.analysis.files.smells.dir}")
    private String rankingDir;
    private final List<InterventionSummary> interventionSummaries = new ArrayList<>();
    private final List<ImportanceSummary> importanceSummaries = new ArrayList<>();
    private final List<QualityAttributesSummary> qualityAttributesSummaries = new ArrayList<>();

    private boolean isLoaded = false;


    public RankingSummaryMapper(JsonInputParser jsonInputParser) {
        this.jsonInputParser = jsonInputParser;
    }

    @Override
    public List<ImportanceSummary> getImportanceSummaries() throws InputLoadingException {
        ensureLoaded();
        return importanceSummaries;
    }

    @Override
    public List<InterventionSummary> getInterventionSummaries() throws InputLoadingException {
        ensureLoaded();
        return interventionSummaries;
    }

    @Override
    public List<QualityAttributesSummary> getQualityAttributeSummaries() throws InputLoadingException {
        ensureLoaded();
        return qualityAttributesSummaries;
    }

    private void ensureLoaded() throws InputLoadingException {
        if (!isLoaded) {
            loadInputData();
            isLoaded = true;
        }
    }

    private void loadInputData() throws InputLoadingException {
        try {
            List<RankingSummaryJsonDto> inputRankingSummaries = jsonInputParser.parseInput(rankingDir + File.separator + RANKING_SUMMARY_FILE_NAME);
            inputRankingSummaries.forEach(inputRankingSummary -> {
                var granularity = inputRankingSummary.granularity();
                var criterion = inputRankingSummary.criterion();
                switch (criterion) {
                    case "Quality Attribute" -> {
                        var qualityAttributeSummary = toQualityAttributesSummary(granularity, inputRankingSummary.details());
                        qualityAttributesSummaries.add(qualityAttributeSummary);
                    }
                    case "Intervention" -> {
                        var interventionSummary = toInterventionSummary(granularity, inputRankingSummary.details());
                        interventionSummaries.add(interventionSummary);
                    }
                    case "Importance" -> {
                        var importanceSummary = toImportanceSummary(granularity, inputRankingSummary.details());
                        importanceSummaries.add(importanceSummary);
                    }
                }
            });
        } catch(IOException e) {
            String errMsg = String.format("Failed to load JSON input. Cause: %s", e.getMessage());
            throw new InputLoadingException(errMsg);
        }
    }

    private ImportanceSummary toImportanceSummary(Granularity granularity, List<RankingSummaryJsonDto.SummaryDetails> summaryDetails) {
        var result = new ImportanceSummary();
        result.setGranularity(granularity);
        summaryDetails.forEach(summaryDetail -> {
            switch (Importance.valueOf(summaryDetail.description())) {
                case Importance.NORMAL -> {
                    result.setPercentNormal(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedNormal(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case Importance.LOW -> {
                    result.setPercentLow(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedLow(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case Importance.HIGH -> {
                    result.setPercentHigh(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedHigh(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case Importance.CRITICAL -> {
                    result.setPercentCritical(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedCritical(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
            }
        });
        return result;
    }

    private InterventionSummary toInterventionSummary(Granularity granularity, List<RankingSummaryJsonDto.SummaryDetails> summaryDetails) {
        var result = new InterventionSummary();
        result.setGranularity(granularity);
        summaryDetails.forEach(summaryDetail -> {
           switch (Intervention.valueOf(summaryDetail.description())) {
               case Intervention.IMMEDIATE -> {
                   result.setPercentImmediate(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                   result.setPercentWeightedImmediate(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
               }
               case Intervention.LOW -> {
                   result.setPercentLow(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                   result.setPercentWeightedLow(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
               }
               case Intervention.PLANNED -> {
                   result.setPercentPlanned(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                   result.setPercentWeightedPlanned(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
               }
               case Intervention.NORMAL -> {
                   result.setPercentNormal(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                   result.setPercentWeightedNormal(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
               }
           }
        });
        return result;
    }

    private QualityAttributesSummary toQualityAttributesSummary(Granularity granularity, List<RankingSummaryJsonDto.SummaryDetails> summaryDetails) {
        var result = new QualityAttributesSummary();
        result.setGranularity(granularity);
        summaryDetails.forEach(summaryDetail -> {
            switch (QualityAttributeName.valueOf(summaryDetail.description())) {
                case QualityAttributeName.COHESION -> {
                    result.setPercentCohesion(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedCohesion(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case QualityAttributeName.COMPLEXITY -> {
                    result.setPercentComplexity(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedComplexity(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case QualityAttributeName.COUPLING -> {
                    result.setPercentCoupling(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedCoupling(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case QualityAttributeName.ENCAPSULATION -> {
                    result.setPercentEncapsulation(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedEncapsulation(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case QualityAttributeName.INHERITANCE -> {
                    result.setPercentInheritance(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedInheritance(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case QualityAttributeName.MAINTAINABILITY -> {
                    result.setPercentMaintainability(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedMaintainability(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case QualityAttributeName.MODULARITY -> {
                    result.setPercentModularity(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedModularity(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case QualityAttributeName.TESTABILITY -> {
                    result.setPercentTestability(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedTestability(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
                case QualityAttributeName.UNDERSTANDABILITY -> {
                    result.setPercentUnderstandability(ParsingUtils.parsePercentValue(summaryDetail.percentual()));
                    result.setPercentWeightedUnderstandability(ParsingUtils.parsePercentValue(summaryDetail.weightDeveloper()));
                }
            }
        });
        return result;
    }
}
