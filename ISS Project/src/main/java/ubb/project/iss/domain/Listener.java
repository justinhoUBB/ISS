package ubb.project.iss.domain;
import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Listener extends BaseEntity<Long> {
    private long member_id;
    private long conference_id;
    private String section_name;
}