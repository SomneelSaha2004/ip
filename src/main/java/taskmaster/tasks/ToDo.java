package taskmaster.tasks;

/**
 * Represents a To-Do task, which is a basic task with only a description and a completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     * The task is initialized as not done by default.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo task with the specified description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone      {@code true} if the task is completed; {@code false} otherwise.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String save() {
        return "T," + super.save();
    }
}
