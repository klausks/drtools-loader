package drtools.loader.in.metrics;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import drtools.loader.in.JsonFileHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MetricThresholdsParser {

    String INPUT_FILENAME = "drtools-metric-thresholds.json";

    private final ObjectMapper mapper = new ObjectMapper();
    private final JsonFileHandler jsonFileHandler;

    public MetricThresholdsParser(JsonFileHandler jsonFileHandler) {
        this.jsonFileHandler = jsonFileHandler;
    }

    public List<MetricThreshold> parse() throws IOException {
        return mapper.readValue(jsonFileHandler.getMetricsAnalysisFile(INPUT_FILENAME), new TypeReference<>() {});
    }

}
