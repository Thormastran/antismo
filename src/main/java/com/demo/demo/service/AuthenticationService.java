package com.demo.demo.service;

import com.demo.demo.dto.LoginRequest;
import com.demo.demo.dto.RegisterRequest;
import com.demo.demo.entity.Account;
import com.demo.demo.exception.exceptions.AuthenticationException;
import com.demo.demo.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Account register(RegisterRequest request) {
        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setPhone(request.getPhone());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setFullName(request.getFullName());
        account.setUsername(request.getUsername());
        account.setAvatarUrl(request.getAvatarUrl());

        // Bổ sung kiểm tra null Enum
        if (request.getGender() == null) {
            throw new IllegalArgumentException("Gender must not be null");
        }
        if (request.getRole() == null) {
            throw new IllegalArgumentException("Role must not be null");
        }

        account.setGender(request.getGender());
        account.setRole(request.getRole());
        account.setCreatedAt(LocalDateTime.now());

        return authenticationRepository.save(account);
    }

    public Account login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );
            Account account = authenticationRepository.findAccountByEmail(loginRequest.getEmail());
            if (account == null) {
                throw new AuthenticationException("User not found");
            }
            return account;
        } catch (Exception e) {
            throw new AuthenticationException("Invalid email or password");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = authenticationRepository.findAccountByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return account;
    }
}
