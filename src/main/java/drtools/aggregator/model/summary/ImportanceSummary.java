package drtools.aggregator.model.summary;

import drtools.aggregator.model.Execution;
import drtools.aggregator.model.Granularity;
import jakarta.persistence.*;

@Entity
public class ImportanceSummary {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Granularity granularity;

    private double percentCritical;
    private double percentWeightedCritical;

    private double percentHigh;
    private double percentWeightedHigh;

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

    public double getPercentCritical() {
        return percentCritical;
    }

    public void setPercentCritical(double percentCritical) {
        this.percentCritical = percentCritical;
    }

    public double getPercentWeightedCritical() {
        return percentWeightedCritical;
    }

    public void setPercentWeightedCritical(double percentWeightedCritical) {
        this.percentWeightedCritical = percentWeightedCritical;
    }

    public double getPercentHigh() {
        return percentHigh;
    }

    public void setPercentHigh(double percentHigh) {
        this.percentHigh = percentHigh;
    }

    public double getPercentWeightedHigh() {
        return percentWeightedHigh;
    }

    public void setPercentWeightedHigh(double percentWeightedHigh) {
        this.percentWeightedHigh = percentWeightedHigh;
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
