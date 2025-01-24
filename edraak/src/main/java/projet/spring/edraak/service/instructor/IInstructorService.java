package projet.spring.edraak.service.instructor;

import org.springframework.web.multipart.MultipartFile;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.Speciality;
import projet.spring.edraak.request.instructor.AddInstructorRequest;
import projet.spring.edraak.request.instructor.InstructorUpdateRequest;

import java.time.LocalDate;
import java.util.List;

public interface IInstructorService {
    Instructor getInstructorById(Long id);
    Instructor addInstructor(AddInstructorRequest instructor , byte[] cv);
    public void deleteInstructor(Long id);
    Instructor updateInstructor(InstructorUpdateRequest instructor, Long id ,byte[] cv);
    Instructor getInstructorByEmail(String email);
    List<Instructor> getInstructorByFirstName(String firstName);
    List<Instructor> getInstructorByLastName(String lastName);
    List<Instructor> getInstructorBySpeciality(Speciality speciality);
    List<Instructor> getInstructorByBirthDate(LocalDate birthDate);
    List<Instructor> getInstructorFirstNameAndLastName(String firstName, String lastName);
    List<Instructor> getAllInstructors();
}
