package drtools.loader.in.smell;

import drtools.loader.model.Granularity;
import drtools.loader.model.criteria.Importance;
import drtools.loader.model.criteria.Intervention;

public record SmellConfigJsonDto(String smell, String description, Granularity granularity, Importance importance, Intervention intervention, String quality) {
}
