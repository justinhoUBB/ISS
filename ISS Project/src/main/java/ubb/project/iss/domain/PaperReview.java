package iss.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class PaperReview extends BaseEntity<Long> {
    private long member_id;
    private long paper_id;
    private int remark; // 1 - strong accept, 2 - accept, 3 - week accept, 4 - borderline paper,
                        // 5 - weak reject, 6 - reject, 7 - strong reject
    private String recommendations;
}
