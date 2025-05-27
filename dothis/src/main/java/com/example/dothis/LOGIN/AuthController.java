package com.example.dothis.LOGIN;

import com.example.dothis.JWT.JwtUtil;
import com.example.dothis.User.User;
import com.example.dothis.User.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest request) {
        System.out.println("AuthController: register called");
        // Kiểm tra username đã tồn tại chưa
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Tạo user mới
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setEmail(request.getEmail());
        user.setCode(request.getCode());
        user.setPhone(request.getPhone());

        // Lưu user
        userRepository.save(user);

        // Tạo token
        String token = jwtUtil.generateToken(user.getUsername());

        // Trả về response
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Userlogin request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

        // Tạo token
        String token = jwtUtil.generateToken(user.getUsername());

        // Trả về response
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<User> getALL(){
        return userRepository.findAll();
    }

}

