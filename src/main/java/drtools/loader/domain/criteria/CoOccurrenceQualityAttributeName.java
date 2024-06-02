package drtools.loader.domain.criteria;

public enum CoOccurrenceQualityAttributeName {
    SIZE("SIZE"),
    COMPLEXITY("COMPLEXITY"),
    COHESION("COHESION"),
    COUPLING("COUPLING");

    public final String value;

    CoOccurrenceQualityAttributeName(String value) {
        this.value = value;
    }
}
