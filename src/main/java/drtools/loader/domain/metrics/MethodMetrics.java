package drtools.loader.domain.metrics;

import drtools.loader.domain.Execution;
import jakarta.persistence.*;

@Entity
@Table(name = MethodMetrics.TABLE_NAME)
public class MethodMetrics {

    public static final String TABLE_NAME = "type_metrics";

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Column(nullable = false)
    private String method;

    private int loc;

    private int cyclo;

    private int calls;

    private int nbd;

    private int parameters;
}
