package com.example.dothis.User;


import com.example.dothis.JWT.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;


    public UserController(UserRepository repo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<User> getALL(){
        return repo.findAll();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }


    @GetMapping("/{id}")
    public Object getById(@PathVariable Long id){
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Optional<ResponseEntity<Object>> delete(@PathVariable Long id){
        return Optional.of(repo.findById(id).map(user -> {
            repo.delete(user);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody User userDetails){
        return repo.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setCode(userDetails.getCode());
            user.setPhone(userDetails.getPhone());
            return ResponseEntity.ok(repo.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }



}
