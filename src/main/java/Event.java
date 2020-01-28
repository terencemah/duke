import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;

public class Event extends Task {

    protected String eventTime;
    protected LocalDate date;
    protected LocalTime time;

    public Event(String name, String input) {
        super(name);

        StringTokenizer st = new StringTokenizer(input);
        String dateString = st.nextToken();
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            try {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
            } catch (DateTimeParseException x) {
                // do nothing
            }
        }

        if (st.hasMoreTokens()) {
            String timeString = st.nextToken();
            try {
                time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
            } catch (DateTimeParseException e) {
                // do nothing
            }
        }

        if (date != null) {
            eventTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (time != null) {
                eventTime = eventTime + " " + time.format(DateTimeFormatter.ofPattern("K.mma"));
            }
        } else {
            eventTime = input;
        }
    }

    public String getTaskDisplay() {
        return "[E][" + getStatusIcon() + "] " + name + " (at: " + eventTime + ")";
    }
    public String getSaveDisplay() {
        return "E|" + (isDone ? "1" : "0") + "|" + name + "|" + eventTime + "\n";
    }
}