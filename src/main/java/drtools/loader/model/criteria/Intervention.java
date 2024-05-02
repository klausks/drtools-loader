package drtools.loader.model.criteria;

public enum Intervention {
    LOW("Low"),
    NORMAL("Normal"),
    PLANNED("Planned"),
    IMMEDIATE("Immediate");

    public final String value;

    Intervention(String value) {
        this.value = value;
    }
}
