package drtools.loader.application.port.out.repository;

import drtools.loader.domain.Execution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
    @Query("select top 1 id from execution where status = 1 order by id desc")
    int findLastCompletedExecutionId();
}
