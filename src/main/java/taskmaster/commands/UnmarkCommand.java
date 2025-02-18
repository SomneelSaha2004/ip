package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;
import taskmaster.tasks.Task;
import taskmaster.exceptions.TaskMasterException;
import java.io.IOException;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param index The index of the task to mark as not done.
     */
    public UnmarkCommand(int index) {
        assert index > 0 : "Task index must be positive.";
        this.index = index;
    }

    /**
     * Executes the command to mark a task as not done.
     *
     * @param tasks   The task list containing tasks.
     * @param storage The storage manager (used to save the updated task list).
     * @return A string message to display in the JavaFX UI.
     * @throws TaskMasterException If the index is out of range or saving fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskMasterException {
        Task task = getTaskAtIndex(tasks);

        task.markAsNotDone();

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new TaskMasterException("Error saving task after unmarking: " + task.getTaskDescription());
        }

        return "🔄 Ok! I've marked this task as not done:\n  " + task;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param tasks The task list containing tasks.
     * @return The task at the given index.
     * @throws TaskMasterException If the index is out of range.
     */
    private Task getTaskAtIndex(TaskList tasks) throws TaskMasterException {
        if (index <= 0 || index > tasks.getTasks().size()) {
            throw new TaskMasterException("❌ Task index out of range. Please provide a valid index.");
        }
        return tasks.getTasks().get(index - 1);
    }

    /**
     * Indicates that the unmark command does not terminate the application.
     *
     * @return {@code false} since the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
