package iss.domain;


import lombok.*;


import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class UserAccount extends BaseEntity<Long> {
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private String affiliation;
    private boolean is_committee_member; // 0 - chair, 1 - co-chair, 2 - regular user
}
