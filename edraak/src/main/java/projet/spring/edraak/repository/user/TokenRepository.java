package projet.spring.edraak.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.user.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer>{
    Optional<Token> findByToken(String token);
}
