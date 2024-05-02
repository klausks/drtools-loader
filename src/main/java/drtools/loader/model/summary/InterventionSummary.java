package drtools.loader.model.summary;

import drtools.loader.model.Execution;
import drtools.loader.model.Granularity;
import jakarta.persistence.*;

@Entity
@Table(name = InterventionSummary.TABLE_NAME)
public class InterventionSummary {

    public static final String TABLE_NAME = "intervention_summary";

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Granularity granularity;

    private double percentImmediate;
    private double percentWeightedImmediate;

    private double percentPlanned;
    private double percentWeightedPlanned;

    private double percentNormal;
    private double percentWeightedNormal;

    private double percentLow;
    private double percentWeightedLow;

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

    public double getPercentImmediate() {
        return percentImmediate;
    }

    public void setPercentImmediate(double percentImmediate) {
        this.percentImmediate = percentImmediate;
    }

    public double getPercentWeightedImmediate() {
        return percentWeightedImmediate;
    }

    public void setPercentWeightedImmediate(double percentWeightedImmediate) {
        this.percentWeightedImmediate = percentWeightedImmediate;
    }

    public double getPercentPlanned() {
        return percentPlanned;
    }

    public void setPercentPlanned(double percentPlanned) {
        this.percentPlanned = percentPlanned;
    }

    public double getPercentWeightedPlanned() {
        return percentWeightedPlanned;
    }

    public void setPercentWeightedPlanned(double percentWeightedPlanned) {
        this.percentWeightedPlanned = percentWeightedPlanned;
    }

    public double getPercentNormal() {
        return percentNormal;
    }

    public void setPercentNormal(double percentNormal) {
        this.percentNormal = percentNormal;
    }

    public double getPercentWeightedNormal() {
        return percentWeightedNormal;
    }

    public void setPercentWeightedNormal(double percentWeightedNormal) {
        this.percentWeightedNormal = percentWeightedNormal;
    }

    public double getPercentLow() {
        return percentLow;
    }

    public void setPercentLow(double percentLow) {
        this.percentLow = percentLow;
    }

    public double getPercentWeightedLow() {
        return percentWeightedLow;
    }

    public void setPercentWeightedLow(double percentWeightedLow) {
        this.percentWeightedLow = percentWeightedLow;
    }
}
