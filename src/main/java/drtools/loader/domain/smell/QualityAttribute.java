package drtools.loader.domain.smell;

import drtools.loader.domain.criteria.QualityAttributeName;
import jakarta.persistence.*;

@Entity
@Table(name = QualityAttribute.TABLE_NAME)
public class QualityAttribute {

    public static final String TABLE_NAME = "quality_attribute";

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private QualityAttributeName name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QualityAttributeName getName() {
        return name;
    }

    public void setName(QualityAttributeName name) {
        this.name = name;
    }
}
