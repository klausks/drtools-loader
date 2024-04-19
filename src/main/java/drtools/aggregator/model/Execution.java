package drtools.aggregator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Execution {

    @Id @GeneratedValue
    private int id;

    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;
}
