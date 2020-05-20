package domain;


import lombok.*;


import javax.persistence.Entity;
import java.time.LocalDate;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Paper extends BaseEntity<Long> {


    private String title;

    /*@NotEmpty
    private List<Long> pc_members_id;*/


    private Long publisher_id;


    private LocalDate dob;
}
