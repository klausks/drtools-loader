package drtools.loader.model.criteria;

public enum QualityAttributeName {
    UNDERSTANDABILITY("Understandability"),
    TESTABILITY("Testability"),
    MAINTAINABILITY("Maintainability"),
    COMPLEXITY("Complexity"),
    MODULARITY("Modularity"),
    COHESION("Cohesion"),
    COUPLING("Coupling"),
    INHERITANCE("Inheritance"),
    ENCAPSULATION("Encapsulation");

    public final String attribute;

    QualityAttributeName(String attribute) {
        this.attribute = attribute;
    }
}
