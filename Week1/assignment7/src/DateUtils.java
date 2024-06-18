import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public static String formatDate(LocalDate dateOfBirth) {
        return dateOfBirth.format(formatter);
    }

    public static LocalDate parseDate(String dateOfBirth) {
        return LocalDate.parse(dateOfBirth, formatter);
    }
}