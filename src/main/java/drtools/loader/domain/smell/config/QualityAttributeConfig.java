package drtools.loader.domain.smell.config;

import drtools.loader.domain.Execution;
import drtools.loader.domain.criteria.QualityImpact;
import drtools.loader.domain.smell.QualityAttribute;
import jakarta.persistence.*;

@Entity
@Table(name = QualityAttributeConfig.TABLE_NAME)
public class QualityAttributeConfig {

    public static final String TABLE_NAME = "quality_attribute_config";

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "last_execution_id", referencedColumnName = "id")
    private Execution lastExecution;

    @ManyToOne
    @JoinColumn(name = "quality_attribute_id", referencedColumnName = "id")
    private QualityAttribute qualityAttribute;

    private double weightDefault;

    private double weightUsed;

    @Enumerated(EnumType.ORDINAL)
    private QualityImpact impactDefault;

    @Enumerated(EnumType.ORDINAL)
    private QualityImpact impactUsed;

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

    public QualityAttribute getQualityAttribute() {
        return qualityAttribute;
    }

    public void setQualityAttribute(QualityAttribute qualityAttribute) {
        this.qualityAttribute = qualityAttribute;
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

    public QualityImpact getImpactDefault() {
        return impactDefault;
    }

    public void setImpactDefault(QualityImpact impactDefault) {
        this.impactDefault = impactDefault;
    }

    public QualityImpact getImpactUsed() {
        return impactUsed;
    }

    public void setImpactUsed(QualityImpact impactUsed) {
        this.impactUsed = impactUsed;
    }
}
