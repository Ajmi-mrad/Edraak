package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.Speciality;

import java.time.LocalDate;
import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor findByEmail(String email);
    List<Instructor> findByLastName(String lastName);
    List<Instructor> findByName(String name);
    List<Instructor> findByNameAndLastName(String name, String lastName);
    List<Instructor> findByBirthDate(LocalDate birthDate);
    boolean existsByEmail(String email);

    List<Instructor> findBySpeciality(Speciality speciality);
}
