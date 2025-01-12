package projet.spring.edraak.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import projet.spring.edraak.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.*;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Student findByEmail(String email);
    List<Student> findByLastName(String lastName);
    List<Student> findByName(String name);
    List<Student> findByNameAndLastName(String name, String lastName);
    List<Student> findByBirthDate(LocalDate birthDate);
    boolean existsByEmail(String email);

}
