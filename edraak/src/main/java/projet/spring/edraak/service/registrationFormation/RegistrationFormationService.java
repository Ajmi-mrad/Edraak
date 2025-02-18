package projet.spring.edraak.service.registrationFormation;

import lombok.Data;
import org.springframework.stereotype.Service;
import projet.spring.edraak.exceptions.RegistrationFormationNotFoundException;
import projet.spring.edraak.model.Classroom;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.RegistrationFormation;
import projet.spring.edraak.model.Student;
import projet.spring.edraak.repository.ClassroomRepository;
import projet.spring.edraak.repository.FormationRepository;
import projet.spring.edraak.repository.RegistrationFormationRepository;
import projet.spring.edraak.repository.StudentRepository;
import projet.spring.edraak.request.registrationFormation.AddRegistrationFormationRequest;
import projet.spring.edraak.request.registrationFormation.ResgitrationFormationUpdateRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class RegistrationFormationService implements IRegistrationFormationService{


    private final RegistrationFormationRepository registrationFormationRepository;
    private final FormationRepository formationRepository;
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;

    public RegistrationFormationService(RegistrationFormationRepository registrationFormationRepository, FormationRepository formationRepository, StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.registrationFormationRepository = registrationFormationRepository;
        this.formationRepository = formationRepository;
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }
    @Override
    public List<RegistrationFormation> getAllRegistrationFormations() {
        try{
            List<RegistrationFormation> registrationFormations = registrationFormationRepository.findAll();
            if(registrationFormations.isEmpty()) {
                throw new RegistrationFormationNotFoundException("No registrationFormations found in the database");
            }
            return registrationFormations;
        } catch (Exception e) {
            throw new RegistrationFormationNotFoundException(e.getMessage());
        }
    }

    @Override
    public RegistrationFormation getRegistrationFormationById(Long id) {
        return registrationFormationRepository.findById(id)
                .orElseThrow(() -> new RegistrationFormationNotFoundException("RegistrationFormation not found"));
    }

    @Override
    public RegistrationFormation addRegistrationFormation(AddRegistrationFormationRequest request) {
        Formation formation = formationRepository.findById(request.getFormationId())
                .orElseThrow(() -> new RegistrationFormationNotFoundException("Formation not found"));
        Classroom classroom = classroomRepository.findById(request.getClassroomId())
                .orElseThrow(() -> new RegistrationFormationNotFoundException("Classroom not found"));
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RegistrationFormationNotFoundException("Student not found"));
        // check if the student is already registered in the formation
        if (registrationFormationRepository.existsByFormationIdAndStudentId(student.getId(), formation.getId())) {
            throw new RegistrationFormationNotFoundException("Student already registered in the formation");
        }
        // check if the classroom is already full in certain date
        /*
        if (registrationFormationRepository.existsByClassroomIdAndFormationId(classroom.getId(), formation.getId())) {
            throw new RegistrationFormationNotFoundException("Classroom already full in this date");
        }
         */
        // check if the training session is aleady taken in the classroom in the same date
        for(int i = 0; i < formation.getTrainingDates().size(); i++) {
            if(registrationFormationRepository.checkIfTrainingSessionIsAlreadyTaken(classroom.getId(),formation.getTrainingDates().get(i))) {
                throw new RegistrationFormationNotFoundException("Training session is already taken in that date and in this classroom");
            }
        }

        return registrationFormationRepository.save(new RegistrationFormation(formation, student, classroom, request.getAbsenceDates()));
    }
    /*
    public RegistrationFormation createRegistrationFormation(AddRegistrationFormationRequest request, Formation formation, Student student, Classroom classroom) {
        return new RegistrationFormation(
                request.getFormationId(),
                request.getStudentId(),
                request.getClassroom()
        );
    }
    */

    @Override
    public void deleteRegistrationFormation(Long id) {
        registrationFormationRepository.findById(id)
                .ifPresentOrElse(registrationFormationRepository::delete, () -> {
                    throw new RegistrationFormationNotFoundException("RegistrationFormation not found");
                });
    }

    @Override
    public RegistrationFormation updateRegistrationFormation(ResgitrationFormationUpdateRequest request, Long id) {
        return Optional.ofNullable(getRegistrationFormationById(id))
                .map(existingRegistrationFormation -> {
                    RegistrationFormation UpdatedRegistrationFormation = updateExistingRegistrationFormation(existingRegistrationFormation, request);
                    return registrationFormationRepository.save(UpdatedRegistrationFormation);
                }).orElseThrow(() -> new RegistrationFormationNotFoundException("RegistrationFormation not found"));
    }
    private RegistrationFormation updateExistingRegistrationFormation(RegistrationFormation existingRegistrationFormation, ResgitrationFormationUpdateRequest request) {
        existingRegistrationFormation.setFormation(formationRepository.findById(request.getIdFormation())
                .orElseThrow(() -> new RegistrationFormationNotFoundException("Formation not found")));
        existingRegistrationFormation.setStudent(studentRepository.findById(request.getIdStudent())
                .orElseThrow(() -> new RegistrationFormationNotFoundException("Student not found")));
        existingRegistrationFormation.setClassroom(classroomRepository.findById(request.getIdClassroom())
                .orElseThrow(() -> new RegistrationFormationNotFoundException("Classroom not found")));

        for (LocalDateTime absenceDate : request.getAbsenceDates()) {
            if (!registrationFormationRepository.checkIfAbsenceDateIsTheSameOfTrainingDate(
                    request.getIdFormation(),
                    request.getIdClassroom(),
                    absenceDate)) {
                throw new RegistrationFormationNotFoundException("Absence date is not the same as the training date");
            }
        }
        existingRegistrationFormation.setAbsenceDates(request.getAbsenceDates());
        return existingRegistrationFormation;
    }

    @Override
    public List<RegistrationFormation> getRegistrationFormationByFormation(Formation formation) {
        return Optional.ofNullable(registrationFormationRepository.findByFormationId(formation.getId()))
                .orElseThrow(() -> new RegistrationFormationNotFoundException("RegistrationFormation not found"));   }
}
