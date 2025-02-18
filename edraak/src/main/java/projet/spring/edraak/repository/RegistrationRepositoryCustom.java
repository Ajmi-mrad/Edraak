package projet.spring.edraak.repository;

import java.time.LocalDateTime;

public interface RegistrationRepositoryCustom {
    Boolean checkIfTrainingSessionIsAlreadyTaken(Long classroomId, LocalDateTime dateTime);
}
