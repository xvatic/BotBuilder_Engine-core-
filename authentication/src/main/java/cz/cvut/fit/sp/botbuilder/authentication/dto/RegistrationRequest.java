package cz.cvut.fit.sp.botbuilder.authentication.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationRequest {
    private String username;
    private String password;
}
