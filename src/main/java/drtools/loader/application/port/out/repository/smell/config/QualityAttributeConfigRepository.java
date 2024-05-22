package drtools.loader.application.port.out.repository.smell.config;

import drtools.loader.domain.smell.config.QualityAttributeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QualityAttributeConfigRepository extends JpaRepository<QualityAttributeConfig, Long> {

    @Query("SELECT attr_config FROM QualityAttributeConfig attr_config INNER JOIN Execution exec WHERE exec.id = ?1")
    List<QualityAttributeConfig> findByExecutionId(int executionId);
}
