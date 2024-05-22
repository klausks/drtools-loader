package drtools.loader.application.port.out.repository.smell.config;

import drtools.loader.domain.smell.config.SmellConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmellConfigRepository extends JpaRepository<SmellConfig, Long> {
}
