package drtools.loader.domain.smell.config;

import drtools.loader.domain.Execution;
import drtools.loader.domain.criteria.Importance;
import drtools.loader.domain.criteria.Intervention;
import drtools.loader.domain.smell.Smell;
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
    private Importance defaultImportance;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Importance usedImportance;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Intervention defaultIntervention;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Intervention usedIntervention;

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

    public Importance getUsedImportance() {
        return usedImportance;
    }

    public void setUsedImportance(Importance usedImportance) {
        this.usedImportance = usedImportance;
    }

    public Intervention getUsedIntervention() {
        return usedIntervention;
    }

    public void setUsedIntervention(Intervention usedIntervention) {
        this.usedIntervention = usedIntervention;
    }

    public Execution getLastExecution() {
        return lastExecution;
    }

    public void setLastExecution(Execution lastExecution) {
        this.lastExecution = lastExecution;
    }

    public Importance getDefaultImportance() {
        return defaultImportance;
    }

    public void setDefaultImportance(Importance importance) {
        this.defaultImportance = importance;
    }

    public Intervention getDefaultIntervention() {
        return defaultIntervention;
    }

    public void setDefaultIntervention(Intervention intervention) {
        this.defaultIntervention = intervention;
    }
}
