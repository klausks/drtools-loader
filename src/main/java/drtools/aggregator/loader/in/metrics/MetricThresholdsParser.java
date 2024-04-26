package drtools.aggregator.loader.in.metrics;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class MetricThresholdsParser {
    String INPUT_FILENAME = "drtools-metric-thresholds.json";
    private final ObjectMapper mapper = new ObjectMapper();

    List<MetricThreshold> parse() throws IOException {
        return mapper.readValue(new File(INPUT_FILENAME), new TypeReference<>() {});
    }

}
