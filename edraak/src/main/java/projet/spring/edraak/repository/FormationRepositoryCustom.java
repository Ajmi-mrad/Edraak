package projet.spring.edraak.repository;

import java.time.LocalDate;

public interface FormationRepositoryCustom {
    Boolean existsByTrainingSessionsExistsBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
}