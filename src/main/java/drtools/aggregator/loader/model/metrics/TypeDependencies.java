package drtools.aggregator.loader.model.metrics;

import jakarta.persistence.*;

import java.util.List;

public class TypeDependencies {
    public static final String TABLE_NAME = "type_dependencies";

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String type;


    private List<String> dependsOn;
}
