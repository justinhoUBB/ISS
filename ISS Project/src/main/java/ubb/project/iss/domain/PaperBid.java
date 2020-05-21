package ubb.project.iss.domain;

import lombok.*;

import javax.persistence.Entity;

// inainte de review, commitee members pot sa aleaga daca vor sa faca review pt un paper sau nu

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class PaperBid extends BaseEntity<Long> {
    private long member_id;
    private long paper_id;
    private String justification;
    private int bid_value; // 1 daca e de acord sa faca review, 0 daca nu-i pasa, si -1 daca nu vrea
}
