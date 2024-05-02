package drtools.loader.model;

public enum Granularity {
    METHOD("METHOD"),
    TYPE("TYPE"),
    NAMESPACE("NAMESPACE");

    public final String granularity;

    Granularity(String granularity) {
        this.granularity = granularity;
    }
}
