package drtools.loader.out.smell.config;

import drtools.loader.model.smell.QualityAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualityAttributeRepository extends JpaRepository<QualityAttribute, Long> {
}
