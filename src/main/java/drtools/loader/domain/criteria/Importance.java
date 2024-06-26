package drtools.loader.domain.criteria;

public enum Importance {
    LOW("LOW"),
    NORMAL("NORMAL"),
    HIGH("HIGH"),
    CRITICAL("CRITICAL");

    public final String importance;

    Importance(String importance) {
        this.importance = importance;
    }
}
