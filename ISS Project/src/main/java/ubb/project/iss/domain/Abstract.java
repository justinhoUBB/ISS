package ubb.project.iss.domain;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

// cred ca ne trebuie clasa separata si pentru asta

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "abstract_table")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Abstract extends BaseEntity<Long>{
    private String title;
    private Long publisher_id;
    @Lob
    @Column(name = "abstract_content")
    private byte[] abstract_content;
}