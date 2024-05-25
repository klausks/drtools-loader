package drtools.loader.application.port.out.repository.smell.config;

import drtools.loader.application.port.out.repository.ExecutionBoundEntityRepository;
import drtools.loader.domain.smell.config.QualityAttributeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QualityAttributeConfigRepository extends ExecutionBoundEntityRepository<QualityAttributeConfig, Long> {
}
