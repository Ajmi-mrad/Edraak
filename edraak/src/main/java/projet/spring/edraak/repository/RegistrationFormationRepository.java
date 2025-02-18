package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projet.spring.edraak.model.RegistrationFormation;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistrationFormationRepository extends JpaRepository<RegistrationFormation, Long>,RegistrationRepositoryCustom {
    @Query("SELECT CASE WHEN COUNT(rf) > 0 THEN true ELSE false END FROM RegistrationFormation rf WHERE rf.formation.id = :formationId AND rf.student.id = :studentId")
    boolean existsByFormationIdAndStudentId(Long formationId, Long studentId);

    boolean existsByClassroomIdAndFormationId(Long formationId,Long classroomId);
    // check if the training sessions exists between start date and end date
    @Query("SELECT CASE WHEN COUNT(rf) > 0 THEN true ELSE false END FROM RegistrationFormation rf WHERE rf.classroom.id = :classroomId AND :dateTime MEMBER OF rf.formation.trainingDates")
    Boolean checkIfTrainingSessionIsAlreadyTaken(@Param("classroomId") Long classroomId,
                                                 @Param("dateTime") LocalDateTime dateTime);

    // check if the absence date is the same of the training date
    @Query("""
    SELECT CASE WHEN COUNT(rf) > 0 THEN true ELSE false END
    FROM RegistrationFormation rf
    WHERE :absenceDate MEMBER OF rf.formation.trainingDates 
      AND rf.formation.id = :formationId
      AND rf.classroom.id = :classroomId
    """)
    Boolean checkIfAbsenceDateIsTheSameOfTrainingDate(@Param("formationId") Long formationId,
                                                      @Param("classroomId") Long classroomId,
                                                      @Param("absenceDate") LocalDateTime absenceDate);

    // check if the training session is aleady taken
    List<RegistrationFormation> findByFormationId(Long formationId);
    RegistrationFormation findByFormationIdAndStudentId(Long formationId, Long studentId);
    //RegistrationFormation findByClassroomIdAndDateTime(Long classroomId, String dateTime);
}
