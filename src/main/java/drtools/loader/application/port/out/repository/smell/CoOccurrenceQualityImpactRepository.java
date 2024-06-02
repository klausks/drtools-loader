package drtools.loader.application.port.out.repository.smell;

import drtools.loader.domain.criteria.CoOccurrenceQualityAttributeName;
import drtools.loader.domain.smell.CoOccurrenceQualityImpact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoOccurrenceQualityImpactRepository extends JpaRepository<CoOccurrenceQualityImpact, Integer> {
    List<CoOccurrenceQualityImpact> findByNameIn(List<CoOccurrenceQualityAttributeName> names);
}
