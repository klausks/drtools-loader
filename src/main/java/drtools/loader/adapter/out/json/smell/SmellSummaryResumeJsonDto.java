package drtools.loader.adapter.out.json.smell;

import com.fasterxml.jackson.annotation.JsonProperty;
import drtools.loader.domain.Granularity;

public record SmellSummaryResumeJsonDto(Granularity granularity,
                                        @JsonProperty("total_smells") int totalSmells,
                                        @JsonProperty("perc_total_smells") String percentTotalSmells,
                                        @JsonProperty("more_than_one_smell") int moreThanOneSmell,
                                        @JsonProperty("perc_more_than_one_smell") String percentMoreThanOneSmell,
                                        int total,
                                        @JsonProperty("perc_total") String percentTotal) {

}