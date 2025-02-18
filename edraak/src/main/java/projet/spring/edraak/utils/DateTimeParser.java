package projet.spring.edraak.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateTimeParser {

    // List of supported date formats
    private static final List<String> SUPPORTED_FORMATS = Arrays.asList(
            "dd-MM-yyyy'T'HH:mm",
            "yyyy-MM-dd'T'HH:mm",
            "dd/MM/yyyy HH:mm",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss"
    );
    // Method to parse a string to LocalDateTime using supported formats
    public static LocalDateTime parseStringToLocalDateTime(String dateString) {
        for (String pattern : SUPPORTED_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Try the next format
            }
        }
        throw new IllegalArgumentException("Invalid date format. Supported formats: " + SUPPORTED_FORMATS);
    }
}