package drtools.loader.adapter.out.json.smell;

import drtools.loader.domain.Granularity;
import drtools.loader.domain.criteria.Importance;
import drtools.loader.domain.criteria.Intervention;

public record SmellConfigJsonDto(String smell, String description, Granularity granularity, Importance importance, Intervention intervention, String quality) {
}
