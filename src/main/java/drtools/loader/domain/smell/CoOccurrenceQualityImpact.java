package drtools.loader.domain.smell;

import drtools.loader.domain.criteria.CoOccurrenceQualityAttributeName;
import jakarta.persistence.*;

@Entity
@Table(name = CoOccurrenceQualityImpact.TABLE_NAME)
public class CoOccurrenceQualityImpact {

    public static final String TABLE_NAME = "smell_co_occurrence_quality_impact";

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private CoOccurrenceQualityAttributeName name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CoOccurrenceQualityAttributeName getName() {
        return name;
    }

    public void setName(CoOccurrenceQualityAttributeName name) {
        this.name = name;
    }
}
