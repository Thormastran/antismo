package antismo.backend.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String usernameOrEmail;
    private String password;
}
