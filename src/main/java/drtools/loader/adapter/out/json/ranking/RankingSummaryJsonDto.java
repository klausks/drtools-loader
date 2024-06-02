package drtools.loader.adapter.out.json.ranking;

import com.fasterxml.jackson.annotation.JsonProperty;
import drtools.loader.domain.Granularity;

import java.util.List;

public record RankingSummaryJsonDto(Granularity granularity, String criterion, List<SummaryDetails> details) {
    public record SummaryDetails(String description, String percentual, @JsonProperty("weight_developer") String weightDeveloper) {}
}
