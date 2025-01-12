package projet.spring.edraak.service.student;

import projet.spring.edraak.exceptions.StudentNotFoundException;
import projet.spring.edraak.model.Section;
import projet.spring.edraak.model.Student;
import projet.spring.edraak.repository.SectionRepository;
import projet.spring.edraak.repository.StudentRepository;
import projet.spring.edraak.request.student.AddStudentRequest;
import projet.spring.edraak.request.student.StudentUpdateRequest;

import java.time.LocalDate;
import java.util.*;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;

    public StudentService(StudentRepository studentRepository, SectionRepository sectionRepository) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Student getStudentById(Long id){
        return studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Student not found"));
    }
    @Override
    public Student addStudent(AddStudentRequest request) {
        Section section = Optional.ofNullable(sectionRepository.findByName(request.getSection().getName()))
                .orElseGet(()-> {
                    Section newSection = new Section(request.getSection().getName());
                    return sectionRepository.save(newSection);
                });
        request.setSection(section);
        return studentRepository.save(createStudent(request,section ));
    }
    public Student createStudent(AddStudentRequest request, Section section){

        return new Student(
                request.getName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getBirthDate(),
                request.getAddress(),
                request.getNationality(),
                request.getNumId(),
                request.getTypeStudent(),
                request.getLevel(),
                section,
                request.getSchool(),
                request.getCompany(),
                request.getJob()
        );
    }


    @Override
    public void deleteStudent(Long id) {
        studentRepository.findById(id)
                .ifPresentOrElse(studentRepository::delete, ()->{
                    throw new StudentNotFoundException("Student not found");
                });
    }

    @Override
    public Student updateStudent(StudentUpdateRequest request, Long id) {
        return studentRepository.findById(id)
                .map(existingStudent -> updateExistingStudent(existingStudent, request))
                .map(studentRepository :: save)
                .orElseThrow(()-> new StudentNotFoundException("Student not found"));
    }
    private Student updateExistingStudent(Student existingStudent, StudentUpdateRequest request){
        existingStudent.setName(request.getName());
        existingStudent.setLastName(request.getLastName());
        existingStudent.setEmail(request.getEmail());
        existingStudent.setPhoneNumber(request.getPhoneNumber());
        existingStudent.setBirthDate(request.getBirthDate());
        existingStudent.setAddress(request.getAddress());
        existingStudent.setNationality(request.getNationality());
        existingStudent.setNumId(request.getNumId());
        existingStudent.setTypeStudent(request.getTypeStudent());
        existingStudent.setLevel(request.getLevel());
        existingStudent.setSection(request.getSection());
        existingStudent.setSchool(request.getSchool());
        existingStudent.setCompany(request.getCompany());
        existingStudent.setJob(request.getJob());

        return existingStudent;
    }

    @Override
    public List<Student> getStudentByBirthDate(LocalDate birthDate) {
        return studentRepository.findByBirthDate(birthDate);
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public List<Student> getStudentByNameAndLastName(String name, String lastName) {
        return studentRepository.findByNameAndLastName(name, lastName);
    }
    @Override
    public List<Student> getStudentByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
