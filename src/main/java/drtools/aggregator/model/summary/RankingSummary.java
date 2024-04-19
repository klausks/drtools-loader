package drtools.aggregator.model.summary;

import drtools.aggregator.model.Execution;
import jakarta.persistence.*;

@Entity
public class RankingSummary {

    @Id @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @OneToOne
    @JoinColumn(name = "quality_attributes_summary_id")
    private QualityAttributesSummary qualityAttributesSummary;

    @OneToOne
    @JoinColumn(name = "importance_summary_id")
    private ImportanceSummary importanceSummary;

    @OneToOne
    @JoinColumn(name = "intervention_summary_id")
    private InterventionSummary interventionSummary;
}
