package drtools.loader.application.port.out.repository.smell;

import drtools.loader.domain.smell.Smell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SmellRepository extends JpaRepository<Smell, Long> {
    List<Smell> findByNameIn(List<String> names);
    Optional<Smell> findByName(String name);
}
