package antismo.backend.controller;

import antismo.backend.dto.UserLoginDTO;
import antismo.backend.entity.User;
import antismo.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    // private final JwtProvider jwtProvider; // Nếu có

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginDTO dto) {
        User user = userService.login(dto.getUsernameOrEmail(), dto.getPassword());
        // String token = jwtProvider.createToken(user); // Nếu dùng JWT
        // return ResponseEntity.ok(Map.of("token", token));
        return ResponseEntity.ok(Map.of("message", "Login success")); // Chưa có JWT
    }
}
