package ubb.project.iss.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

// cred ca ne trebuie clasa separata si pentru asta

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "abstract_table")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class AbstractTable extends BaseEntity<Long>{
    private String abstractTitle;
    @Column()
    private Long publisher_id;
    @Lob
    @Column(name = "abstract_content")
    private byte[] abstract_content;
}
