package taskmaster.commands;

import taskmaster.exceptions.TaskMasterException;
import taskmaster.storage.Storage;
import taskmaster.tasks.Task;
import taskmaster.utils.TaskList;

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
        if (index <= 0 || index > tasks.getTasks().size()) {
            throw new TaskMasterException("❌ Error: Task index out of range.");
        }

        Task task = tasks.getTasks().get(index - 1);
        task.markAsNotDone();

        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new TaskMasterException("❌ Error: Failed to save task after unmarking.");
        }

        return "✅ Task marked as not done:\n  " + task;
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
