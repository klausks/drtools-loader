package drtools.loader.model.criteria;

public enum Criteria {
    IMPORTANCE("Importance"),
    INTERVENTION("Intervention"),
    QUALITY("Quality");

    public final String criterion;

    Criteria(String criterion) {
        this.criterion = criterion;
    }
}
