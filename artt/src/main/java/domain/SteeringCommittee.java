package domain;

import lombok.*;

import javax.persistence.Entity;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor

@Data
@Entity
public class SteeringCommittee extends User {
    public SteeringCommittee(String first_name, String last_name, String email) {
    }
}
