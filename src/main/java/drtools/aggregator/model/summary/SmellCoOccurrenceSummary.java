package drtools.aggregator.model.summary;

import drtools.aggregator.model.Execution;
import drtools.aggregator.model.smell.SmellCoOccurrence;
import jakarta.persistence.*;

@Entity
@Table(name = SmellCoOccurrenceSummary.TABLE_NAME)
public class SmellCoOccurrenceSummary {

    public static final String TABLE_NAME = "smell_co_occurrence_summary";

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private int instances;

    @ManyToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    private double percentual;

    @ManyToOne
    @JoinColumn(name = "co_occurrence_id", referencedColumnName = "id")
    private SmellCoOccurrence coOccurrence;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    public SmellCoOccurrence getCoOccurrence() {
        return coOccurrence;
    }

    public void setCoOccurrence(SmellCoOccurrence coOccurrence) {
        this.coOccurrence = coOccurrence;
    }
}
