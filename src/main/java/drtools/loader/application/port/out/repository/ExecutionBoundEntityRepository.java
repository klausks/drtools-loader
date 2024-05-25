package drtools.loader.application.port.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ExecutionBoundEntityRepository<T, ID> extends JpaRepository<T, ID> {
    @Query("SELECT config FROM #{#entityName} config INNER JOIN Execution exec WHERE exec.id = ?1")
    List<T> findByExecutionId(int executionId);
}
