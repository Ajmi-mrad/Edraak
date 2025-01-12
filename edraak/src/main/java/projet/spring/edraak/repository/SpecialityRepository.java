package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    Speciality findByName(String name);
    Boolean existsByName(String name);
}

