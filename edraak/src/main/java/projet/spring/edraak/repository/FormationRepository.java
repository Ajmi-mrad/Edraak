package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.TypeFormation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long>, FormationRepositoryCustom{
    @Query("SELECT f FROM Formation f JOIN FETCH f.instructor WHERE f.id = :id")
    Formation findByIdWithInstructor(Long id); // fetch instructor with formation
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Formation f WHERE :startDate MEMBER OF f.trainingDates AND :endDate MEMBER OF f.trainingDates")
    Boolean existsTrainingSessionsBetweenStartDateAndEndDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    Formation findByName(String name);
    // check if the training sessions exists between start date and end date
    //@Query("SELECT COUNT(f) FROM Formation f WHERE :startDate MEMBER OF f.trainingDates AND :endDate MEMBER OF f.trainingDates")
    //Boolean existsByTrainingSessionsExistsBetweenStartDateAndEndDate(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
    //check if the number of training sessions equals to the number of training dates (DurationTotal / DurationOfSession == TrainingDates.size())
    //Boolean existsByDurationTotalAndDurationOfSessionAndTrainingDatesSize(String durationTotal, String durationOfSession, Integer trainingDatesSize);

    List<Formation> findByTypeFormation(TypeFormation typeFormation);
}
