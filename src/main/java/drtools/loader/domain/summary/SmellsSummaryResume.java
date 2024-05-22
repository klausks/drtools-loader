package drtools.loader.domain.summary;

import drtools.loader.domain.Execution;
import drtools.loader.domain.Granularity;
import jakarta.persistence.*;

@Entity
@Table(name = SmellsSummaryResume.TABLE_NAME)
public class SmellsSummaryResume {

    public static final String TABLE_NAME = "smells_summary_resume";

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Granularity granularity;

    @Column(nullable = false)
    private int total;

    private double percentTotal;

    @Column(nullable = false)
    private int totalSmells;

    private double percentTotalSmells;

    private int moreThanOneSmell;

    private double percentMoreThanOneSmell;

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

    public double getPercentTotal() {
        return percentTotal;
    }

    public void setPercentTotal(double percentTotal) {
        this.percentTotal = percentTotal;
    }

    public int getTotalSmells() {
        return totalSmells;
    }

    public void setTotalSmells(int totalSmells) {
        this.totalSmells = totalSmells;
    }

    public double getPercentTotalSmells() {
        return percentTotalSmells;
    }

    public void setPercentTotalSmells(double percentTotalSmells) {
        this.percentTotalSmells = percentTotalSmells;
    }

    public int getMoreThanOneSmell() {
        return moreThanOneSmell;
    }

    public void setMoreThanOneSmell(int moreThanOneSmell) {
        this.moreThanOneSmell = moreThanOneSmell;
    }

    public double getPercentMoreThanOneSmell() {
        return percentMoreThanOneSmell;
    }

    public void setPercentMoreThanOneSmell(double percentMoreThanOneSmell) {
        this.percentMoreThanOneSmell = percentMoreThanOneSmell;
    }
}
