package drtools.loader.application.port.out.repository.metrics;

import drtools.loader.model.metrics.MetricsThresholds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricsThresholdsRepository extends JpaRepository<MetricsThresholds, Long> {
}
