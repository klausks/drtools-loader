package drtools.loader.adapter.out.json.smell;

import com.fasterxml.jackson.annotation.JsonProperty;
import drtools.loader.domain.Granularity;

import java.util.List;

public record SmellsSummaryJsonDto(Granularity granularity, List<SmellSummaryDto> smells) {
    public record SmellSummaryDto(String smell, int instances, @JsonProperty("perc_instances") String percentInstances) {}
}
