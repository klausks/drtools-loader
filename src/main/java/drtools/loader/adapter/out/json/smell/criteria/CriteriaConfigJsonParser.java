package drtools.loader.adapter.out.json.smell.criteria;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import drtools.loader.adapter.out.json.JsonFileHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CriteriaConfigJsonParser {

    static final String DEFAULT_CRITERIA_INPUT_FILE = "drtools-criteria-default.json";
    static final String USED_CRITERIA_INPUT_FILE = "drtools-criteria-used.json";
    private final ObjectMapper mapper;
    private final JsonFileHandler jsonFileHandler;

    public CriteriaConfigJsonParser(JsonFileHandler jsonFileHandler) {
        this.jsonFileHandler = jsonFileHandler;
        mapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS).build();
    }


    public List<CriteriaConfigJson> parseDefault() throws IOException {
        return mapper.readValue(jsonFileHandler.getSmellsAnalysisFile(DEFAULT_CRITERIA_INPUT_FILE), new TypeReference<>() {});
    }

    public List<CriteriaConfigJson> parseUsed() throws IOException {
        return mapper.readValue(jsonFileHandler.getSmellsAnalysisFile(USED_CRITERIA_INPUT_FILE), new TypeReference<>() {});
    }
}
