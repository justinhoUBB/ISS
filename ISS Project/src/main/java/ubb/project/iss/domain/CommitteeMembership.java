package ubb.project.iss.domain;

import lombok.*;

import javax.persistence.Entity;

// clasa asta e ca sa stim care useri sunt membri de comitet (din moment ce nu putem folosi liste)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class CommitteeMembership extends BaseEntity<Long> {
    private long user_id;
    private long committee_id;
}
