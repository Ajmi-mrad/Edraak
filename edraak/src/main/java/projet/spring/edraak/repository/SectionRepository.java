package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.spring.edraak.model.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findByName(String name);
    Boolean existsByName(String name);
}
