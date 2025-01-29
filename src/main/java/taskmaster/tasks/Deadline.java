package taskmaster.tasks;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Represents a deadline task with a description, a completion status,
 * and a due date.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a new deadline task with the specified description and due date.
     * The task is initialized as not done by default.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time of the deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new deadline task with the specified description, completion status,
     * and due date.
     *
     * @param description The description of the deadline task.
     * @param isDone      {@code true} if the task is completed; {@code false} otherwise.
     * @param by          The due date and time of the deadline task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the due date and time of the deadline task.
     *
     * @return The due date and time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     * This includes the task type ("D"), its completion status, the description,
     * and the due date and time.
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toString() + ")";
    }

    /**
     * Returns a serialized string representation of the deadline task,
     * formatted for saving to a file.
     *
     * The format includes the task type ("D"), its completion status, the description,
     * and the due date and time in ISO_LOCAL_DATE_TIME format.
     *
     * @return A string representation of the deadline task suitable for file storage.
     */
    @Override
    public String save() {
        return "D," + super.save() + "," + by.toString();
    }

    /**
     * Checks if the deadline task is due on the specified date.
     *
     * The method compares the date portion of the task's due date with the input date.
     *
     * @param date The date to check if the deadline task is due.
     * @return {@code true} if the deadline task is due on the specified date; {@code false} otherwise.
     */
    @Override
    public boolean isDue(LocalDate date) {
        return by.toLocalDate().isEqual(date);
    }
}
