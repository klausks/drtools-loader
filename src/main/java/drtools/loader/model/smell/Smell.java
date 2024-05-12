package drtools.loader.model.smell;

import drtools.loader.model.Granularity;
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
    private String name;

    String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Granularity granularity;

    @ManyToMany
    @JoinTable(
            name = "smell_quality_impact",
            joinColumns = @JoinColumn(name = "smell_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "quality_attribute_id", referencedColumnName = "id")
    )
    private List<QualityAttribute> impactedQualityAttributes;

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

    public List<QualityAttribute> getImpactedQualityAttributes() {
        return impactedQualityAttributes;
    }

    public void setImpactedQualityAttributes(List<QualityAttribute> impactedQualityAttributes) {
        this.impactedQualityAttributes = impactedQualityAttributes;
    }
}
