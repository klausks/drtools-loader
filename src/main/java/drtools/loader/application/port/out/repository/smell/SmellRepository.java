package drtools.loader.application.port.out.repository.smell;

import drtools.loader.domain.smell.Smell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmellRepository extends JpaRepository<Smell, Long> {
}
