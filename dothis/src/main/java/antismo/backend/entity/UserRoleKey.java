package antismo.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleKey implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;
}
