package drtools.loader.adapter.out.json.smell;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.adapter.out.json.JsonInputParser;
import drtools.loader.application.port.out.input.InputSmellSummaryResumesProvider;
import drtools.loader.domain.summary.SmellsSummaryResume;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SmellsSummaryResumeMapper implements InputSmellSummaryResumesProvider {
    private static final String SMELL_SUMMARY_RESUME = "drtools-summary-smells-resume.json";

    private final List<SmellsSummaryResume> smellSummaryResumes = new ArrayList<>();

    private boolean isLoaded = false;
    private final JsonInputParser jsonInputParser;

    @Value("${drtools.analysis.files.smells.dir}")
    private String SMELLS_DIR;

    public SmellsSummaryResumeMapper(JsonInputParser jsonInputParser) {
        this.jsonInputParser = jsonInputParser;
    }

    @Override
    public List<SmellsSummaryResume> getInputSmellSummaryResumes() throws InputLoadingException {
        ensureLoaded();
        return smellSummaryResumes;
    }

    private void ensureLoaded() throws InputLoadingException {
        if (!isLoaded) {
            loadInputData();
            isLoaded = true;
        }
    }

    private void loadInputData() throws InputLoadingException {
        try {
            List<SmellSummaryResumeJsonDto> input = jsonInputParser.parseInput(SMELLS_DIR + File.separator + SMELL_SUMMARY_RESUME);
            input.forEach(inputSummaryResume -> {
                var summaryResume = new SmellsSummaryResume();
                summaryResume.setGranularity(inputSummaryResume.granularity());
                summaryResume.setTotal(inputSummaryResume.total());
                summaryResume.setTotalSmells(inputSummaryResume.totalSmells());
                summaryResume.setMoreThanOneSmell(inputSummaryResume.moreThanOneSmell());
                summaryResume.setPercentMoreThanOneSmell(Double.parseDouble("0." + inputSummaryResume.percentMoreThanOneSmell().replaceAll("[,%]", "")));
                summaryResume.setPercentTotal(Double.parseDouble("0." + inputSummaryResume.percentTotal().replaceAll("[,%]", "")));
                summaryResume.setPercentTotalSmells(Double.parseDouble("0." + inputSummaryResume.percentTotalSmells().replaceAll("[,%]", "")));
                smellSummaryResumes.add(summaryResume);
            });
        } catch(IOException e) {
            String errMsg = String.format("Failed to load JSON input. Cause: %s", e.getMessage());
            throw new InputLoadingException(errMsg);
        }
    }
}
