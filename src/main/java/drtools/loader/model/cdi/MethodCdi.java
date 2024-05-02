package drtools.loader.model.cdi;

import drtools.loader.model.Execution;
import drtools.loader.model.smell.Smell;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = MethodCdi.TABLE_NAME)
public class MethodCdi {
    public static final String TABLE_NAME = "method_cdi";

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Column(nullable = false)
    private String methodName;

    @Column(nullable = false)
    private double cdi;

    private double normalizedSeverity;

    private double normalizedRepresentativity;

    private double normalizedQuality;

    private double normalizedIntervention;

    @ManyToMany
    @JoinTable(
            name = "method_smell_occurrence",
            joinColumns = @JoinColumn(name = "method_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "smell_id", referencedColumnName = "id")
    )
    private List<Smell> smells;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public double getCdi() {
        return cdi;
    }

    public void setCdi(double cdi) {
        this.cdi = cdi;
    }

    public double getNormalizedSeverity() {
        return normalizedSeverity;
    }

    public void setNormalizedSeverity(double normalizedSeverity) {
        this.normalizedSeverity = normalizedSeverity;
    }

    public double getNormalizedRepresentativity() {
        return normalizedRepresentativity;
    }

    public void setNormalizedRepresentativity(double normalizedRepresentativity) {
        this.normalizedRepresentativity = normalizedRepresentativity;
    }

    public double getNormalizedQuality() {
        return normalizedQuality;
    }

    public void setNormalizedQuality(double normalizedQuality) {
        this.normalizedQuality = normalizedQuality;
    }

    public double getNormalizedIntervention() {
        return normalizedIntervention;
    }

    public void setNormalizedIntervention(double normalizedIntervention) {
        this.normalizedIntervention = normalizedIntervention;
    }

    public List<Smell> getSmells() {
        return smells;
    }

    public void setSmells(List<Smell> smells) {
        this.smells = smells;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }
}
