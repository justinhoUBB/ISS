package domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class BaseEntity<ID extends Serializable> implements Serializable
{
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected ID id;
}
