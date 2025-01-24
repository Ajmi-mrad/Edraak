package projet.spring.edraak.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
    Session findByName(String name);
    boolean existsByName(String name);


}
