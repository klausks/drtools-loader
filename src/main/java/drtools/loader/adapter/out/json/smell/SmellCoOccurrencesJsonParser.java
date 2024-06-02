package drtools.loader.adapter.out.json.smell;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import drtools.loader.adapter.out.json.JsonFileHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SmellCoOccurrencesJsonParser {
    static final String INPUT_FILE = "drtools-cooccurrences-smells.json";
    private final ObjectMapper mapper;
    private final JsonFileHandler jsonFileHandler;

    public SmellCoOccurrencesJsonParser(JsonFileHandler jsonFileHandler) {
        this.jsonFileHandler = jsonFileHandler;
        mapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS).build();
    }


    public List<SmellCoOccurrencesByCategoryJson> parse() throws IOException {
        return mapper.readValue(jsonFileHandler.getSmellsAnalysisFile(INPUT_FILE), new TypeReference<>() {});
    }
}
