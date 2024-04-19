package drtools.aggregator.model.summary;

import drtools.aggregator.model.Execution;
import drtools.aggregator.model.Granularity;
import jakarta.persistence.*;

@Entity
public class SmellsSummaryResume {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Granularity granularity;

    @Column(nullable = false)
    private int total;

    private double percTotal;

    @Column(nullable = false)
    private int totalSmells;

    private double percTotalSmells;

    private int moreThanOneSmell;

    private double percMoreThanOneSmell;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getPercTotal() {
        return percTotal;
    }

    public void setPercTotal(double percTotal) {
        this.percTotal = percTotal;
    }

    public int getTotalSmells() {
        return totalSmells;
    }

    public void setTotalSmells(int totalSmells) {
        this.totalSmells = totalSmells;
    }

    public double getPercTotalSmells() {
        return percTotalSmells;
    }

    public void setPercTotalSmells(double percTotalSmells) {
        this.percTotalSmells = percTotalSmells;
    }

    public int getMoreThanOneSmell() {
        return moreThanOneSmell;
    }

    public void setMoreThanOneSmell(int moreThanOneSmell) {
        this.moreThanOneSmell = moreThanOneSmell;
    }

    public double getPercMoreThanOneSmell() {
        return percMoreThanOneSmell;
    }

    public void setPercMoreThanOneSmell(double percMoreThanOneSmell) {
        this.percMoreThanOneSmell = percMoreThanOneSmell;
    }
}
