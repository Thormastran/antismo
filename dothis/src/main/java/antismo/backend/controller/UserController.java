package antismo.backend.controller;

import antismo.backend.dto.*;
import antismo.backend.entity.User;
import antismo.backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@Tag(name = "User API", description = "Quản lý người dùng")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable Integer userId, @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.ok(userService.updateUser(userId, dto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<List<String>> getRoles(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getRolesOfUser(userId));
    }
}