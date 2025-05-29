package antismo.backend.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String fullName;
    private String phone;
    private String avatarUrl;
}