package projet.spring.edraak.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.time.LocalDate;

public class FormationRepositoryImpl implements FormationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean existsByTrainingSessionsExistsBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
        // If the count of dates outside the range is 0, then all trainingDates are inside.
        String queryString = """
            SELECT COUNT(ftd) = 0
            FROM Formation f 
            JOIN f.trainingDates ftd
            WHERE ftd < :startDate OR ftd > :endDate
        """;
        Query query = entityManager.createQuery(queryString);
        query.setParameter("startDate", startDate.atStartOfDay());
        query.setParameter("endDate", endDate.atTime(23, 59));
        // ken khdmt touul stratdate and enddate it will be treated as 2024-02-11T00:00:00
        // par exmpl startDate = 2024-02-11 is interpreted as 2024-02-11T00:00:0 and endDate = 2024-02-15 is interpreted as 2024-02-15T00:00:00
        //We fix this issue by explicitly setting:
        //startDate.atStartOfDay() → Converts LocalDate to LocalDateTime at 00:00:00 (start of the day).
        //endDate.atTime(23, 59) → Converts LocalDate to LocalDateTime at 23:59:59 (end of the day).
        // maaneha dima lmochkl fi endDate
        return (Boolean) query.getSingleResult();
    }
}