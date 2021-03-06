package ubb.project.iss.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Paper extends BaseEntity<Long>{

    private String title;
    private Long publisher_id;
    private Long conference_id;
    private String list_of_authors;
    private String keywords;
    private boolean reviewed;


    private String content; // user-ul trebuie sa poata sa uploadeze si paper-ul in sine (cel mai probabil in format .docx sau .pdf)
                            // deci trebuie convertit in byte[] aici (BLOB in bd)
}
