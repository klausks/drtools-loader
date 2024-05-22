package drtools.loader.application.port.out.repository.smell.config;

import drtools.loader.domain.smell.config.InterventionConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterventionConfigrepository extends JpaRepository<InterventionConfig, Long> {
}
