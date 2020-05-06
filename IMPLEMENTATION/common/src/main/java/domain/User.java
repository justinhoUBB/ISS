package domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class User extends  BaseEntity<Long>{
    private String name;
    private String email;
    private String username;
    private String website;
    private String affiliation;
    private String password;
}
