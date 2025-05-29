package antismo.backend.repository;



import antismo.backend.entity.User;
import antismo.backend.entity.UserRole;
import antismo.backend.entity.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {
    List<UserRole> findByUser(User user);
}