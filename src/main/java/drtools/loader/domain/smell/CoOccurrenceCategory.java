package drtools.loader.domain.smell;

public enum CoOccurrenceCategory {
    INTER_COMPONENT("INTER_COMPONENT"),
    TYPE("TYPE"),
    METHOD("METHOD");

    public final String value;

    CoOccurrenceCategory(String value) {
        this.value = value;
    }
}
