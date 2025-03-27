package projet.spring.edraak.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}