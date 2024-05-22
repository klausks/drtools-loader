package drtools.loader.domain.criteria;

public enum QualityAttributeName {
    UNDERSTANDABILITY("UNDERSTANDABILITY"),
    TESTABILITY("TESTABILITY"),
    MAINTAINABILITY("MAINTAINABILITY"),
    COMPLEXITY("COMPLEXITY"),
    MODULARITY("MODULARITY"),
    COHESION("COHESION"),
    COUPLING("COUPLING"),
    INHERITANCE("INHERITANCE"),
    ENCAPSULATION("ENCAPSULATION");

    public final String attribute;

    QualityAttributeName(String attribute) {
        this.attribute = attribute;
    }
}
