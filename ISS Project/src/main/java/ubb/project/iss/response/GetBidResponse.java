package ubb.project.iss.response;

import lombok.*;
import ubb.project.iss.domain.Conference;
import ubb.project.iss.domain.UserAccount;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetBidResponse {
    private Conference conference;
    private LocalDate bid_deadline;
}