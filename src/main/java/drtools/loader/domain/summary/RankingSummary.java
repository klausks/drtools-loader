package drtools.loader.domain.summary;

import drtools.loader.domain.Execution;
import drtools.loader.domain.Granularity;
import jakarta.persistence.*;

@Entity
@Table(name = RankingSummary.TABLE_NAME)
public class RankingSummary {

    public static final String TABLE_NAME = "ranking_summary";

    @Id @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Enumerated(EnumType.ORDINAL)
    private Granularity granularity;

    @OneToOne
    @JoinColumn(name = "quality_attributes_summary_id")
    private QualityAttributesSummary qualityAttributesSummary;

    @OneToOne
    @JoinColumn(name = "importance_summary_id")
    private ImportanceSummary importanceSummary;

    @OneToOne
    @JoinColumn(name = "intervention_summary_id")
    private InterventionSummary interventionSummary;

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

    public QualityAttributesSummary getQualityAttributesSummary() {
        return qualityAttributesSummary;
    }

    public void setQualityAttributesSummary(QualityAttributesSummary qualityAttributesSummary) {
        this.qualityAttributesSummary = qualityAttributesSummary;
    }

    public ImportanceSummary getImportanceSummary() {
        return importanceSummary;
    }

    public void setImportanceSummary(ImportanceSummary importanceSummary) {
        this.importanceSummary = importanceSummary;
    }

    public InterventionSummary getInterventionSummary() {
        return interventionSummary;
    }

    public void setInterventionSummary(InterventionSummary interventionSummary) {
        this.interventionSummary = interventionSummary;
    }

    public Granularity getGranularity() {
        return granularity;
    }

    public void setGranularity(Granularity granularity) {
        this.granularity = granularity;
    }
}
