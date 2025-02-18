package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.FormationDTO;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.Speciality;

import java.time.LocalDate;
import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    //@Query("SELECT i FROM Instructor i LEFT JOIN FETCH i.formations WHERE i.id = :id")
    //Instructor findByIdWithFormations(Long id);
    @Query("SELECT f FROM Formation f WHERE f.instructor.id = :id")
    List<Formation> findFormationsByInstructorId(Long id);
    Instructor findByEmail(String email);
    List<Instructor> findByLastName(String lastName);
    List<Instructor> findByName(String name);
    List<Instructor> findByNameAndLastName(String name, String lastName);
    List<Instructor> findByBirthDate(LocalDate birthDate);
    boolean existsByEmail(String email);

    List<Instructor> findBySpeciality(Speciality speciality);
}
