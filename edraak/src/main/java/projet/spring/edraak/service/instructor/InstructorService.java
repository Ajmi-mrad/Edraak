package projet.spring.edraak.service.instructor;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projet.spring.edraak.exceptions.InstructorNotFoundException;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.Speciality;
import projet.spring.edraak.repository.InstructorRepository;
import projet.spring.edraak.repository.SpecialityRepository;
import projet.spring.edraak.request.instructor.AddInstructorRequest;
import projet.spring.edraak.request.instructor.InstructorUpdateRequest;
import projet.spring.edraak.service.speciality.ISpecialityService;
import projet.spring.edraak.service.speciality.SpecialityService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class InstructorService implements IInstructorService{
    private final InstructorRepository instructorRepository;
    private final SpecialityRepository specialityRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository,SpecialityRepository specialityRepository) {
        this.instructorRepository = instructorRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found"));
    }

    @Override
    public Instructor addInstructor(AddInstructorRequest request,byte[] cv) {
        Speciality speciality = Optional.ofNullable(specialityRepository.findByName(request.getSpeciality().getName()))
                .orElseGet(() -> {
                    Speciality newSpeciality = new Speciality(request.getSpeciality().getName());
                    return specialityRepository.save(newSpeciality);
                });
        request.setSpeciality(speciality);

        return instructorRepository.save(createInstructor(request, speciality,cv));
    }

    public Instructor createInstructor(AddInstructorRequest request, Speciality speciality,byte[] cv) {
        return new Instructor(
                request.getName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getBirthDate(),
                request.getAddress(),
                request.getNationality(),
                request.getNumId(),
                speciality,
                cv

        );
    }
    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.findById(id)
                .ifPresentOrElse(instructorRepository::delete, () -> {
                    throw new InstructorNotFoundException("Instructor not found");
                });
    }

    @Override
    public Instructor updateInstructor(InstructorUpdateRequest request, Long id,byte[] cv) {
        return Optional.ofNullable(getInstructorById(id)).map(existingInstructor -> {
            Instructor updatedInstructor = updateExistingInstructor(existingInstructor, request,cv);
            return instructorRepository.save(updatedInstructor);
        }).orElseThrow(() -> new InstructorNotFoundException("Instructor not found"));
    }

    private Instructor updateExistingInstructor(Instructor existingInstructor, InstructorUpdateRequest request,byte[] cv) {
        existingInstructor.setName(request.getName());
        existingInstructor.setLastName(request.getLastName());
        existingInstructor.setEmail(request.getEmail());
        existingInstructor.setPhoneNumber(request.getPhoneNumber());
        existingInstructor.setBirthDate(request.getBirthDate());
        existingInstructor.setAddress(request.getAddress());
        existingInstructor.setNationality(request.getNationality());
        existingInstructor.setNumId(request.getNumId());
        existingInstructor.setSpeciality(request.getSpeciality());
        existingInstructor.setCv(cv);
        return existingInstructor;
    }
    @Override
    public Instructor getInstructorByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }

    @Override
    public List<Instructor> getInstructorByFirstName(String firstName) {
        return instructorRepository.findByName(firstName);
    }

    @Override
    public List<Instructor> getInstructorByLastName(String lastName) {
        return instructorRepository.findByLastName(lastName);
    }

    @Override
    public List<Instructor> getInstructorBySpeciality(Speciality speciality) {

        return instructorRepository.findBySpeciality(speciality);
    }

    @Override
    public List<Instructor> getInstructorByBirthDate(LocalDate birthDate) {

        return instructorRepository.findByBirthDate(birthDate);
    }

    @Override
    public List<Instructor> getAllInstructors() {

        return instructorRepository.findAll();
    }

    @Override
    public List<Instructor> getInstructorFirstNameAndLastName(String firstName, String lastName) {

        return instructorRepository.findByNameAndLastName(firstName, lastName);
    }



}
