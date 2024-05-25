package drtools.loader.domain.smell.config;

import drtools.loader.domain.Execution;
import drtools.loader.domain.criteria.Intervention;
import jakarta.persistence.*;

@Entity
@Table(name = InterventionConfig.TABLE_NAME)
public class InterventionConfig {

    public static final String TABLE_NAME = "intervention_priority_config";

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "last_execution_id", referencedColumnName = "id")
    private Execution lastExecution;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Intervention intervention;

    private double weightDefault;

    private double weightUsed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Execution getLastExecution() {
        return lastExecution;
    }

    public void setLastExecution(Execution lastExecution) {
        this.lastExecution = lastExecution;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention interventionDescription) {
        this.intervention = interventionDescription;
    }

    public double getWeightDefault() {
        return weightDefault;
    }

    public void setWeightDefault(double weightDefault) {
        this.weightDefault = weightDefault;
    }

    public double getWeightUsed() {
        return weightUsed;
    }

    public void setWeightUsed(double weightUsed) {
        this.weightUsed = weightUsed;
    }
}
