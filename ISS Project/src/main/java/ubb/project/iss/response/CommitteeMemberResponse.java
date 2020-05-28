package ubb.project.iss.response;

import lombok.*;
import ubb.project.iss.domain.ConferenceCommittee;
import ubb.project.iss.domain.UserAccount;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommitteeMemberResponse {
    private ConferenceCommittee conferenceCommittee;
    private boolean status;

}