import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    protected LocalDateTime date;

    public Date(String str) throws ChairdException {
        this.date = convertStrToLocalDateTime(str);
    }

    private LocalDateTime convertStrToLocalDateTime(String str) throws ChairdException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            return LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new ChairdException("Hey your date is invalid, please input a valid date " +
                    "in this format (yyyy-mm-dd HHmm) Example: 2019-07-11 1700");
        }
    }

    public String storageLineStr() {
        DateTimeFormatter storageFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return date.format(storageFormat);
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
