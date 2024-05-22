package drtools.loader.application.port.out.repository.smell.config;

import drtools.loader.domain.smell.QualityAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualityAttributeRepository extends JpaRepository<QualityAttribute, Long> {
}
