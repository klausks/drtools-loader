package drtools.loader.domain.smell.config;

import drtools.loader.domain.Execution;
import drtools.loader.domain.criteria.Importance;
import jakarta.persistence.*;

@Entity
@Table(name = ImportanceConfig.TABLE_NAME)
public class ImportanceConfig {
    public static final String TABLE_NAME = "importance_config";

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "last_execution_id", referencedColumnName = "id")
    private Execution lastExecution;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Importance importance;

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

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
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
