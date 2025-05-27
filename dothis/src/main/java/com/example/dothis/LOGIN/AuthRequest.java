package com.example.dothis.LOGIN;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AuthRequest {
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Password is required")
    private String password;

    @Email(message = "Email invalid")
    private String email;

    @NotBlank(message = "Code is required")
    @Pattern(regexp = "SE\\d{5}", message = "invalid code")
    private String code;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^(84|0[3|5|7|8|9])[0-9]{8}$", message = "Phone invalid")
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
} 