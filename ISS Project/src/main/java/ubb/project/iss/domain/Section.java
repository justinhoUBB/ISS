package ubb.project.iss.domain;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Section extends BaseEntity<Long> {
    private long supervisor_id;
    private long conference_id;
    private LocalDate start_time;
}
