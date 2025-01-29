package taskmaster.tasks;

import java.time.LocalDate;

/**
 * Represents a task with a description and a completion status.
 *
 * This is an abstract class, and specific task types (e.g., ToDo, Deadline, Event)
 * must extend it to provide additional functionality.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a new task with the specified description.
     * The task is initialized as not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      {@code true} if the task is completed; {@code false} otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     * The icon is "X" if the task is done, and a blank space (" ") if not done.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is completed; {@code false} otherwise.
     */
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getTaskDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task.
     * This includes the status icon and the description.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the task in a serialized format suitable for saving to a file.
     * The format includes the completion status (1 for done, 0 for not done)
     * and the description, separated by a comma.
     *
     * @return A string representation of the task for file storage.
     */
    public String save() {
        return ((isDone) ? 1 : 0) + "," + description;
    }

    /**
     * Checks if the task is due on a specific date.
     * This method is intended to be overridden by subclasses (e.g., Deadline, Event).
     * For tasks that have no due date (e.g., ToDo), this method returns {@code false}.
     *
     * @param date The date to check if the task is due.
     * @return {@code true} if the task is due on the specified date; {@code false} otherwise.
     */
    public boolean isDue(LocalDate date) {
        return false;
    }
}
