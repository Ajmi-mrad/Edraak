package projet.spring.edraak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.TypeFormation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long>, FormationRepositoryCustom {
    Formation findByName(String name);
    // check if the training sessions exists between start date and end date
    //Boolean existsByTrainingSessionsExistsBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
    // check if the number of training sessions equals to the number of training dates (DurationTotal / DurationOfSession == TrainingDates.size())
    //Boolean existsByDurationTotalAndDurationOfSessionAndTrainingDatesSize(String durationTotal, String durationOfSession, Integer trainingDatesSize);

    List<Formation> findByTypeFormation(TypeFormation typeFormation);
}
