package domain;


import lombok.*;


import javax.persistence.Entity;


@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User  extends BaseEntity<Long>{
    private String first_name;
    private String last_name;
    private String email;
}
