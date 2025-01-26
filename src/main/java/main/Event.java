package main;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an event task with a description, a completion status,
 * and a start and end date and time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new event task with the specified description, start date, and end date.
     * The task is initialized as not done by default.
     *
     * @param description The description of the event task.
     * @param from        The start date and time of the event task.
     * @param to          The end date and time of the event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new event task with the specified description, completion status,
     * start date, and end date.
     *
     * @param description The description of the event task.
     * @param isDone      {@code true} if the task is completed; {@code false} otherwise.
     * @param from        The start date and time of the event task.
     * @param to          The end date and time of the event task.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     * This includes the task type ("E"), its completion status, the description,
     * and the start and end date and time.
     *
     * @return A formatted string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a serialized string representation of the event task,
     * formatted for saving to a file.
     *
     * The format includes the task type ("E"), its completion status, the description,
     * and the start and end date and time in ISO_LOCAL_DATE_TIME format.
     *
     * @return A string representation of the event task suitable for file storage.
     */
    @Override
    public String save() {
        return "E," + super.save() + "," + from + "," + to;
    }

    /**
     * Checks if the event is due on a specific date.
     *
     * The method compares the date portion of the event's start date with the input date.
     *
     * @param date The date to check if the event is due.
     * @return {@code true} if the event starts on the specified date; {@code false} otherwise.
     */
    @Override
    public boolean isDue(LocalDate date) {
        return from.toLocalDate().isEqual(date);
    }
}
