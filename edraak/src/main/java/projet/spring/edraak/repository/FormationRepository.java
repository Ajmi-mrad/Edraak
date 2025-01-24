package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.TypeFormation;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {
    Formation findByName(String name);
    Boolean existsByName(String name);
    List<Formation> findByTypeFormation(TypeFormation typeFormation);
}
