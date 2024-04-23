package drtools.aggregator.loader.model.summary;

import drtools.aggregator.loader.model.Execution;
import drtools.aggregator.loader.model.Granularity;
import jakarta.persistence.*;

@Entity
@Table(name = QualityAttributesSummary.TABLE_NAME)
public class QualityAttributesSummary {

    public static final String TABLE_NAME = "quality_attributes_summary";

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "execution_id", referencedColumnName = "id")
    private Execution execution;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Granularity granularity;

    private double percentModularity;
    private double percentWeightedModularity;

    private double percentUnderstandability;
    private double percentWeightedUnderstandability;

    private double percentMaintainability;
    private double percentWeightedMaintainability;

    private double percentTestability;
    private double percentWeightedTestability;

    private double percentComplexity;
    private double percentWeightedComplexity;

    private double percentCohesion;
    private double percentWeightedCohesion;

    private double percentCoupling;
    private double percentWeightedCoupling;

    private double percentInheritance;
    private double percentWeightedInheritance;

    private double percentEncapsulation;
    private double percentWeightedEncapsulation;

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

    public Granularity getGranularity() {
        return granularity;
    }

    public void setGranularity(Granularity granularity) {
        this.granularity = granularity;
    }

    public double getPercentModularity() {
        return percentModularity;
    }

    public void setPercentModularity(double percentModularity) {
        this.percentModularity = percentModularity;
    }

    public double getPercentWeightedModularity() {
        return percentWeightedModularity;
    }

    public void setPercentWeightedModularity(double percentWeightedModularity) {
        this.percentWeightedModularity = percentWeightedModularity;
    }

    public double getPercentUnderstandability() {
        return percentUnderstandability;
    }

    public void setPercentUnderstandability(double percentUnderstandability) {
        this.percentUnderstandability = percentUnderstandability;
    }

    public double getPercentWeightedUnderstandability() {
        return percentWeightedUnderstandability;
    }

    public void setPercentWeightedUnderstandability(double percentWeightedUnderstandability) {
        this.percentWeightedUnderstandability = percentWeightedUnderstandability;
    }

    public double getPercentMaintainability() {
        return percentMaintainability;
    }

    public void setPercentMaintainability(double percentMaintainability) {
        this.percentMaintainability = percentMaintainability;
    }

    public double getPercentWeightedMaintainability() {
        return percentWeightedMaintainability;
    }

    public void setPercentWeightedMaintainability(double percentWeightedMaintainability) {
        this.percentWeightedMaintainability = percentWeightedMaintainability;
    }

    public double getPercentTestability() {
        return percentTestability;
    }

    public void setPercentTestability(double percentTestability) {
        this.percentTestability = percentTestability;
    }

    public double getPercentWeightedTestability() {
        return percentWeightedTestability;
    }

    public void setPercentWeightedTestability(double percentWeightedTestability) {
        this.percentWeightedTestability = percentWeightedTestability;
    }

    public double getPercentComplexity() {
        return percentComplexity;
    }

    public void setPercentComplexity(double percentComplexity) {
        this.percentComplexity = percentComplexity;
    }

    public double getPercentWeightedComplexity() {
        return percentWeightedComplexity;
    }

    public void setPercentWeightedComplexity(double percentWeightedComplexity) {
        this.percentWeightedComplexity = percentWeightedComplexity;
    }

    public double getPercentCohesion() {
        return percentCohesion;
    }

    public void setPercentCohesion(double percentCohesion) {
        this.percentCohesion = percentCohesion;
    }

    public double getPercentWeightedCohesion() {
        return percentWeightedCohesion;
    }

    public void setPercentWeightedCohesion(double percentWeightedCohesion) {
        this.percentWeightedCohesion = percentWeightedCohesion;
    }

    public double getPercentCoupling() {
        return percentCoupling;
    }

    public void setPercentCoupling(double percentCoupling) {
        this.percentCoupling = percentCoupling;
    }

    public double getPercentWeightedCoupling() {
        return percentWeightedCoupling;
    }

    public void setPercentWeightedCoupling(double percentWeightedCoupling) {
        this.percentWeightedCoupling = percentWeightedCoupling;
    }

    public double getPercentInheritance() {
        return percentInheritance;
    }

    public void setPercentInheritance(double percentInheritance) {
        this.percentInheritance = percentInheritance;
    }

    public double getPercentWeightedInheritance() {
        return percentWeightedInheritance;
    }

    public void setPercentWeightedInheritance(double percentWeightedInheritance) {
        this.percentWeightedInheritance = percentWeightedInheritance;
    }

    public double getPercentEncapsulation() {
        return percentEncapsulation;
    }

    public void setPercentEncapsulation(double percentEncapsulation) {
        this.percentEncapsulation = percentEncapsulation;
    }

    public double getPercentWeightedEncapsulation() {
        return percentWeightedEncapsulation;
    }

    public void setPercentWeightedEncapsulation(double percentWeightedEncapsulation) {
        this.percentWeightedEncapsulation = percentWeightedEncapsulation;
    }
}
