package drtools.loader.domain.criteria;

public enum Intervention {
    LOW("LOW"),
    NORMAL("NORMAL"),
    PLANNED("PLANNED"),
    IMMEDIATE("IMMEDIATE");

    public final String value;

    Intervention(String value) {
        this.value = value;
    }
}
