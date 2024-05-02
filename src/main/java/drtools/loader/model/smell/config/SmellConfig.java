package drtools.loader.model.smell.config;

import drtools.loader.model.Execution;
import drtools.loader.model.criteria.Importance;
import drtools.loader.model.criteria.Intervention;
import drtools.loader.model.smell.Smell;
import jakarta.persistence.*;

@Entity
@Table
public class SmellConfig {

    public static final String TABLE_NAME = "smell_config";

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "smell_id", referencedColumnName = "id")
    private Smell smell;

    @ManyToOne
    @JoinColumn(name = "last_execution_id", referencedColumnName = "id")
    private Execution lastExecution;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Importance importance;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Intervention intervention;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Smell getSmell() {
        return smell;
    }

    public void setSmell(Smell smell) {
        this.smell = smell;
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

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
}
