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
public class Repartition extends BaseEntity<Long> {
    private long user_id;
    private long section_id;
}
