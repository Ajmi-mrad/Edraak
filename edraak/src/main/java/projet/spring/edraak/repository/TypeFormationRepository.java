package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.TypeFormation;

public interface TypeFormationRepository extends JpaRepository<TypeFormation, Long> {
    TypeFormation findByName(String name);
    boolean existsByName(String name);
}
