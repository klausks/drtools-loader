package drtools.loader.model.smell.config;

import drtools.loader.model.Execution;
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
    private String importanceDescription;

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

    public String getImportanceDescription() {
        return importanceDescription;
    }

    public void setImportanceDescription(String importanceDescription) {
        this.importanceDescription = importanceDescription;
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
