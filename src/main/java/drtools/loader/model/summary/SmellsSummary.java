package drtools.loader.model.summary;

import drtools.loader.model.Execution;
import drtools.loader.model.Granularity;
import drtools.loader.model.smell.Smell;
import jakarta.persistence.*;

@Entity
@Table(name = SmellsSummary.TABLE_NAME)
public class SmellsSummary {

    public static final String TABLE_NAME = "smells_summary";

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Granularity granularity;

    @ManyToOne
    @JoinColumn(name = "smell_id", nullable = false)
    private Smell smell;

    @Column(nullable = false)
    private int totalInstances;

    @Column(nullable = false)
    private double percentInstances;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    public Granularity getGranularity() {
        return granularity;
    }

    public void setGranularity(Granularity granularity) {
        this.granularity = granularity;
    }

    public Smell getSmell() {
        return smell;
    }

    public void setSmell(Smell smell) {
        this.smell = smell;
    }

    public int getTotalInstances() {
        return totalInstances;
    }

    public void setTotalInstances(int totalInstances) {
        this.totalInstances = totalInstances;
    }

    public double getPercentInstances() {
        return percentInstances;
    }

    public void setPercentInstances(double percentInstances) {
        this.percentInstances = percentInstances;
    }
}
