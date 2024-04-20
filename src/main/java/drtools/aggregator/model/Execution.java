package drtools.aggregator.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = Execution.TABLE_NAME)
public class Execution {

    public static final String TABLE_NAME = "execution";

    @Id @GeneratedValue
    private int id;

    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;
}
