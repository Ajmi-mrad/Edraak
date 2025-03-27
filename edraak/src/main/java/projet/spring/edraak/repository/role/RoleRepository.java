package projet.spring.edraak.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.role.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String roleName);
}
