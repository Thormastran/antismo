package antismo.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "UserRoles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    @EmbeddedId
    private UserRoleKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;
}
