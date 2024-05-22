package drtools.loader.domain.criteria;

public enum Criteria {
    IMPORTANCE("IMPORTANCE"),
    INTERVENTION("INTERVENTION"),
    QUALITY("QUALITY");

    public final String criterion;

    Criteria(String criterion) {
        this.criterion = criterion;
    }
}
