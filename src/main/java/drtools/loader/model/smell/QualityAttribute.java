package drtools.loader.model.smell;

import drtools.loader.model.criteria.QualityAttributeName;
import drtools.loader.model.criteria.QualityImpact;
import jakarta.persistence.*;

@Entity
@Table(name = QualityAttribute.TABLE_NAME)
public class QualityAttribute {

    public static final String TABLE_NAME = "quality_attribute";

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QualityAttributeName name;

    @Column(nullable = false)
    private double weight;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private QualityImpact impact;

}
