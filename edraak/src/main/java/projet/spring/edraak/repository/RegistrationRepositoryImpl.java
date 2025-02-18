package projet.spring.edraak.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;

public class RegistrationRepositoryImpl implements RegistrationRepositoryCustom{
    // check if the training session is already taken
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean checkIfTrainingSessionIsAlreadyTaken(Long classroomId, LocalDateTime dateTime) {
        // Check if the training session is already taken by maping training Date one by one and check it with the given date
        String queryString = """
            SELECT COUNT(rf)
            FROM RegistrationFormation rf
            WHERE rf.classroom.id = :classroomId
            AND :dateTime MEMBER OF rf.formation.trainingDates
        """;

        Long count = entityManager.createQuery(queryString, Long.class)
                .setParameter("classroomId", classroomId)
                .setParameter("dateTime", dateTime)
                .getSingleResult();

        return count > 0;
    }
}
