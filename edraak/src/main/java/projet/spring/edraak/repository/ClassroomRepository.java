package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findByName(String name);
    Boolean existsByName(String name);
}
