package drtools.aggregator.loader.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class MetricThresholdsParser {
    String INPUT_FILENAME = "drtools-metric-thresholds.json";
    ObjectMapper mapper = new ObjectMapper();
}
