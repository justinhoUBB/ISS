package ubb.project.iss.response;

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
    private String role;
    private String message;
    private int centerId;
}