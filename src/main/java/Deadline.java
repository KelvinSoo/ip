import java.time.LocalDateTime;

/**
 * Deadline class.
 * Used to represent a deadline task.
 *
 * @author KelvinSoo
 * @version Level-4
 *
 */
public class Deadline extends Task {
    private String dateLine;

    public Deadline(String description, String dateLine) {
        super(description);
        this.dateLine = dateLine;
    }

    public Deadline(String description, LocalDateTime localDateTime) {
        super(description);
        this.dateLine = String.format("%s of %s %s, %s%s",
                localDateTime.getDayOfMonth(),
                localDateTime.getMonth().toString(),
                localDateTime.getYear(),
                localDateTime.getHour() < 12 ? localDateTime.getHour() : localDateTime.getHour() - 12,
                localDateTime.getHour() < 12 ? "am" : "pm");
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", super.getDescription(), this.dateLine);
    }

}
