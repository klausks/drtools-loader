package drtools.loader.adapter.out.json.smell;

import com.fasterxml.jackson.annotation.JsonProperty;
import drtools.loader.domain.smell.CoOccurrenceCategory;

import java.util.List;

public record SmellCoOccurrencesByCategoryJson(CoOccurrenceCategory category, List<SmellCoOccurrenceJson> data) {
    public record SmellCoOccurrenceJson(String description, int instances, String percentual, String smells, @JsonProperty("impacts_on") String impactsOn) { }
}
