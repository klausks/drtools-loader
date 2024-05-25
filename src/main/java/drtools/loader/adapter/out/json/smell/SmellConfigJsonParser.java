package drtools.loader.adapter.out.json.smell;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import drtools.loader.adapter.out.json.JsonFileHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SmellConfigJsonParser {

    private static final String DEFAULT_SMELLS_CONFIG_FILE = "drtools-smells-default.json";
    private static final String USED_SMELLS_CONFIG_FILE = "drtools-smells-default.json";

    private final JsonFileHandler jsonFileHandler;
    private final ObjectMapper objectMapper = new ObjectMapper().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

    public SmellConfigJsonParser(JsonFileHandler jsonFileHandler) {
        this.jsonFileHandler = jsonFileHandler;
    }

    public List<SmellConfigJsonDto> parseDefault() throws IOException {
        return objectMapper.readValue(jsonFileHandler.getSmellsAnalysisFile(DEFAULT_SMELLS_CONFIG_FILE), new TypeReference<>() {});
    }

    public List<SmellConfigJsonDto> parseUsed() throws IOException {
        return objectMapper.readValue(jsonFileHandler.getSmellsAnalysisFile(USED_SMELLS_CONFIG_FILE), new TypeReference<>() {});
    }

}
