package com.demo.demo.dto;

import com.demo.demo.enums.Gender;
import com.demo.demo.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String phone;
    private String password;
    private String fullName;
    private String username;
    private String avatarUrl;
    private Gender gender; // enum
    private Role role;     // enum
}
