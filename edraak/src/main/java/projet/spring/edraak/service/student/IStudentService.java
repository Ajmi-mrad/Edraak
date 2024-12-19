package projet.spring.edraak.service.student;

import projet.spring.edraak.model.Student;
import projet.spring.edraak.request.AddStudentRequest;
import projet.spring.edraak.request.StudentUpdateRequest;

import java.time.LocalDate;
import java.util.List;

public interface IStudentService {
    Student getStudentById(Long id);
    Student addStudent(AddStudentRequest student);
    public void deleteStudent(Long id);
    Student updateStudent(StudentUpdateRequest student, Long id );
    List<Student>  getStudentByBirthDate(LocalDate birthDate);
    List<Student> getStudentByName(String name);
    List<Student> getStudentByNameAndLastName(String name,String lastName);

    List<Student> getStudentByLastName(String lastName);
    Student getStudentByEmail(String email);
    List<Student>  getAllStudents();

}
