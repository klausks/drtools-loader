package drtools.loader.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = Execution.TABLE_NAME)
public class Execution {

    public static final String TABLE_NAME = "execution";

    @Id @GeneratedValue
    private int id;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private ExecutionStatus status;

    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
