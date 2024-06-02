package drtools.loader.adapter.out.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonInputParser {
    private final ObjectMapper objectMapper;

    public JsonInputParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> parseInput(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<>(){});
    }
}
