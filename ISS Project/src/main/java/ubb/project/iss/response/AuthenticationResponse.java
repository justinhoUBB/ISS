package ubb.project.iss.response;

import lombok.*;
import ubb.project.iss.domain.UserAccount;
import lombok.*;
import ubb.project.iss.domain.UserAccount;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String status;
    private Boolean isError;
    private UserAccount userAccount;
    private Boolean role;
    private long userId;
    private long centerId;
}