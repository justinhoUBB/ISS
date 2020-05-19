package domain;

import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.Entity;


@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User  extends BaseEntity<Long>{
    @NonNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private String email;
}
