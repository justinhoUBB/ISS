package ubb.project.iss.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

// cred ca ne trebuie clasa separata si pentru asta

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Abstract extends BaseEntity<Long>{
    private String title;
    private Long publisher_id;
    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] content;
}