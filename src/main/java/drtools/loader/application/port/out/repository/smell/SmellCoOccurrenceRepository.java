package drtools.loader.application.port.out.repository.smell;

import drtools.loader.domain.smell.SmellCoOccurrence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmellCoOccurrenceRepository extends JpaRepository<SmellCoOccurrence, Long> {
}
