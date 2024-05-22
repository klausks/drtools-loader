package drtools.loader.application.port.out.repository.smell.config;

import drtools.loader.domain.smell.config.ImportanceConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportanceConfigRepository extends JpaRepository<ImportanceConfig, Long> {
}
