package projet.spring.edraak.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static LocalDate parseStringToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}
