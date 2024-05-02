package drtools.loader.model.criteria;

public enum QualityImpact {
    LOW("LOW"),
    NORMAL("NORMAL"),
    HIGH("HIGH"),
    CRITICAL("CRITICAL");

    public final String impact;

    QualityImpact(String impact) {
        this.impact = impact;
    }
}
