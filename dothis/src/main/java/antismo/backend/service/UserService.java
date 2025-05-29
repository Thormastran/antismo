package antismo.backend.service;

import antismo.backend.entity.*;
import antismo.backend.repository.*;
import antismo.backend.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()
                || userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Username or email already exists");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        // Gán role mặc định
        Role defaultRole = roleRepository.findByName("Member")
                .orElseThrow(() -> new RuntimeException("Role 'Member' not found"));
        UserRoleKey key = new UserRoleKey(user.getId(), defaultRole.getId());
        UserRole userRole = new UserRole(key, user, defaultRole);
        userRoleRepository.save(userRole);

        return user;
    }

    public User login(String usernameOrEmail, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(usernameOrEmail);
        if (!optionalUser.isPresent()) {
            optionalUser = userRepository.findByEmail(usernameOrEmail);
        }
        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }

    public User updateUser(Integer userId, UserUpdateDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        user.setAvatarUrl(dto.getAvatarUrl());
        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public List<String> getRolesOfUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<UserRole> userRoles = userRoleRepository.findByUser(user);
        return userRoles.stream().map(ur -> ur.getRole().getName()).collect(Collectors.toList());
    }
}
