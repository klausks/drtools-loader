package drtools.aggregator.loader.model.smell;

import jakarta.persistence.*;

@Entity
@Table(name = CoOccurrenceQualityImpact.TABLE_NAME)
public class CoOccurrenceQualityImpact {

    public static final String TABLE_NAME = "smell_co_occurrence_quality_impact";

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

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
}
