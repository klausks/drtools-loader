package drtools.aggregator.loader.model.metrics;

import drtools.aggregator.loader.model.Execution;
import jakarta.persistence.*;

@Entity
@Table(name = CyclicDependency.TABLE_NAME)
public class CyclicDependency {

    public static final String TABLE_NAME = "cyclic_dependency";

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Column(nullable = false)
    private String from;

    @Column(nullable = false)
    private String to;
}
