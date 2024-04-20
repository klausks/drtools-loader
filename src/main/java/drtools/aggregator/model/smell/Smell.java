package drtools.aggregator.model.smell;

import drtools.aggregator.model.Granularity;
import drtools.aggregator.model.QualityAttribute;
import drtools.aggregator.model.criteria.Importance;
import drtools.aggregator.model.criteria.Intervention;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = Smell.TABLE_NAME)
public class Smell {

    public static final String TABLE_NAME = "smell";

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    String name;

    String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    Granularity granularity;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    Importance importance;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    Intervention intervention;

    @ManyToMany
    @JoinTable(
            name = "smell_quality_impact",
            joinColumns = @JoinColumn(name = "smell_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "quality_attribute_id", referencedColumnName = "id")
    )
    List<QualityAttribute> impactedQualityAttributes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Granularity getGranularity() {
        return granularity;
    }

    public void setGranularity(Granularity granularity) {
        this.granularity = granularity;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }

    public List<QualityAttribute> getImpactedQualityAttributes() {
        return impactedQualityAttributes;
    }

    public void setImpactedQualityAttributes(List<QualityAttribute> impactedQualityAttributes) {
        this.impactedQualityAttributes = impactedQualityAttributes;
    }
}