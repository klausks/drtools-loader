package drtools.loader.adapter.out.json.smell;

import drtools.loader.adapter.out.exception.InputLoadingException;
import drtools.loader.adapter.out.json.JsonInputParser;
import drtools.loader.application.port.out.input.InputSmellsSummaryProvider;
import drtools.loader.domain.smell.Smell;
import drtools.loader.domain.summary.SmellSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SmellsSummaryMapper implements InputSmellsSummaryProvider {
    private static final String SMELL_SUMMARIES_FILE = "drtools-summary-smells.json";

    private final List<SmellSummary> smellSummaries = new ArrayList<>();

    private boolean isLoaded = false;
    private final JsonInputParser jsonInputParser;

    @Value("${drtools.analysis.files.smells.dir}")
    private String SMELLS_DIR;

    public SmellsSummaryMapper(JsonInputParser jsonInputParser) {
        this.jsonInputParser = jsonInputParser;
    }

    @Override
    public List<SmellSummary> getSmellsSummaries() throws InputLoadingException {
        ensureLoaded();
        return smellSummaries;
    }

    private void ensureLoaded() throws InputLoadingException {
        if (!isLoaded) {
            loadInputData();
            isLoaded = true;
        }
    }

    private void loadInputData() throws InputLoadingException {
        try {
            List<SmellsSummaryJsonDto> input = jsonInputParser.parseInput(SMELLS_DIR + File.separator + SMELL_SUMMARIES_FILE);
            input.forEach(inputSummary -> {
                var granularity = inputSummary.granularity();
                inputSummary.smells().forEach(smellSummary -> {
                    var summary = new SmellSummary();
                    summary.setGranularity(granularity);
                    summary.setTotalInstances(smellSummary.instances());
                    summary.setPercentInstances(Double.parseDouble("0." + smellSummary.percentInstances().replaceAll("[,%]", "")));
                    var smell = new Smell();
                    smell.setName(smellSummary.smell());
                    summary.setSmell(smell);
                    smellSummaries.add(summary);
                });
            });
        } catch(IOException e) {
            String errMsg = String.format("Failed to load JSON input. Cause: %s", e.getMessage());
            throw new InputLoadingException(errMsg);
        }
    }
}
