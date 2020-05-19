package domain;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Conference extends BaseEntity<Long>{
    @NotEmpty
    private List<User> sc_members_id;
    @NotEmpty
    private List<String> topics;
    @NonNull
    private LocalDate starting_date;
    @NonNull
    private LocalDate paper_deadline;
}
