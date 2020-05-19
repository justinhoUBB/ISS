package domain;

import com.sun.istack.NotNull;
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
public class Paper extends BaseEntity<Long> {

    @NotNull
    private String title;

    /*@NotEmpty
    private List<Long> pc_members_id;*/

    @NotNull
    private Long publisher_id;

    @NotNull
    private LocalDate dob;
}
