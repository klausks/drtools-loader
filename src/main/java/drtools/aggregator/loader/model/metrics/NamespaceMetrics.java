package drtools.aggregator.loader.model.metrics;


import drtools.aggregator.loader.model.Execution;
import jakarta.persistence.*;

@Entity
@Table(name = NamespaceMetrics.TABLE_NAME)
public class NamespaceMetrics {

    public static final String TABLE_NAME = "metrics_threshold_config";

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Column(nullable = false)
    private String namespace;

    private int numberOfClasses;

    private int numberOfAbstractClasses;

    private int afferentCoupling;

    private int efferentCoupling;

    private double instability;

    private double abstractness;

    private double distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public int getNumberOfAbstractClasses() {
        return numberOfAbstractClasses;
    }

    public void setNumberOfAbstractClasses(int numberOfAbstractClasses) {
        this.numberOfAbstractClasses = numberOfAbstractClasses;
    }

    public int getAfferentCoupling() {
        return afferentCoupling;
    }

    public void setAfferentCoupling(int afferentCoupling) {
        this.afferentCoupling = afferentCoupling;
    }

    public int getEfferentCoupling() {
        return efferentCoupling;
    }

    public void setEfferentCoupling(int efferentCoupling) {
        this.efferentCoupling = efferentCoupling;
    }

    public double getInstability() {
        return instability;
    }

    public void setInstability(double instability) {
        this.instability = instability;
    }

    public double getAbstractness() {
        return abstractness;
    }

    public void setAbstractness(double abstractness) {
        this.abstractness = abstractness;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
