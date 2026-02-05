package chaird.task;

import chaird.exception.ChairdException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Date class stores date and time values from a specific string format that
 * is used by the tasks in the application.
 */
public class Date {
    protected LocalDateTime date;

    /**
     * Constructs a Date object by parsing the input string using the time format stated
     * by the application.
     *
     * @param str the date string to parse (i.e 2020-03-14 1800)
     * @throws ChairdException if input string does not match format
     */
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

    /**
     * Returns a string representation of this date suitable for file storage.
     * Uses the format yyyy-MM-dd HHmm.
     *
     * @return the date formatted for storage (i.e 2019-07-11 1700)
     */
    public String storageLineStr() {
        DateTimeFormatter storageFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return date.format(storageFormat);
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
