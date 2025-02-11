package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.RegistrationFormation;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistrationFormationRepository extends JpaRepository<RegistrationFormation, Long> {
    boolean existsByFormationIdAndStudentId(Long formationId, Long studentId);
    boolean existsByClassroomIdAndFormationId(Long formationId,Long classroomId);
    // check if the training sessions exists between start date and end date
    Boolean checkIfTrainingSessionsExistsBetweenStartDateAndEndDateAndClassroomId(String startDate, String endDate, Long classroomId);
    // check if the training session is aleady taken
    Boolean checkIfTrainingSessionIsAlreadyTaken(Long classroomId, LocalDateTime dateTime);
    List<RegistrationFormation> findByFormationId(Long formationId);
    RegistrationFormation findByFormationIdAndStudentId(Long formationId, Long studentId);
    RegistrationFormation findByClassroomIdAndDateTime(Long classroomId, String dateTime);
}
