package ubb.project.iss.response;

import lombok.*;
import ubb.project.iss.domain.PaperSubmission;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaperSubmissionResponse {
    private PaperSubmission paperSubmission;
    private Boolean status;
    private long conference_id;

}
