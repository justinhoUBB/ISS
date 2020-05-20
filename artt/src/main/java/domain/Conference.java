package domain;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;


@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Conference extends BaseEntity<Long>{

    private List<User> sc_members_id;

    private List<String> topics;

    private List<Integer> number_of_seats;

    private LocalDate starting_date;

    private LocalDate paper_deadline;
}
