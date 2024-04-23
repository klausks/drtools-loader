package drtools.aggregator.loader.model.smell;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = SmellCoOccurrence.TABLE_NAME)
public class SmellCoOccurrence {
    public static final String TABLE_NAME = "smell_co_occurrence";

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private CoOccurrenceCategory category;

    @ManyToMany
    @JoinTable(
            name = "smell_co_occurrence_smell_mapping",
            joinColumns = @JoinColumn(name = "co_occurrence_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "smell_id", referencedColumnName = "id")
    )
    private List<Smell> smells;

    @ManyToMany
    @JoinTable(
            name = "smell_co_occurence_quality_impact_mapping",
            joinColumns = @JoinColumn(name = "co_occurrence_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "co_occurrence_quality_impact_id", referencedColumnName = "id")
    )
    private List<CoOccurrenceQualityImpact> impactsOnQuality;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CoOccurrenceCategory getCategory() {
        return category;
    }

    public void setCategory(CoOccurrenceCategory category) {
        this.category = category;
    }

    public List<Smell> getSmells() {
        return smells;
    }

    public void setSmells(List<Smell> smells) {
        this.smells = smells;
    }

    public List<CoOccurrenceQualityImpact> getImpactsOnQuality() {
        return impactsOnQuality;
    }

    public void setImpactsOnQuality(List<CoOccurrenceQualityImpact> impactsOnQuality) {
        this.impactsOnQuality = impactsOnQuality;
    }
}
