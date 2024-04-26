package drtools.aggregator.loader.in.smell.criteria;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import drtools.aggregator.loader.in.JsonFileHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CriteriaParser {

    static final String DEFAULT_CRITERIA_INPUT_FILE = "drtools-criteria-default.json";
    static final String USED_CRITERIA_INPUT_FILE = "drtools-criteria-used.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final JsonFileHandler jsonFileHandler;

    public CriteriaParser(JsonFileHandler jsonFileHandler) {
        this.jsonFileHandler = jsonFileHandler;
    }


    public List<CriteriaConfig> parseDefault() throws IOException {
        return mapper.readValue(jsonFileHandler.getSmellsAnalysisFile(DEFAULT_CRITERIA_INPUT_FILE), new TypeReference<>() {});
    }

    public List<CriteriaConfig> parseUsed() throws IOException {
        return mapper.readValue(jsonFileHandler.getSmellsAnalysisFile(USED_CRITERIA_INPUT_FILE), new TypeReference<>() {});
    }
}
