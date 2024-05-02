package drtools.loader.out.smell.config;

import drtools.loader.model.smell.config.SmellConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmellConfigRepository extends JpaRepository<SmellConfig, Long> {
}
