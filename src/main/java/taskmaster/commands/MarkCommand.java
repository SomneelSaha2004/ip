package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;
import taskmaster.tasks.Task;
import taskmaster.exceptions.TaskMasterException;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param tasks   The task list containing tasks.
     * @param storage The storage manager (used to save the updated task list).
     * @return A string message to display in the JavaFX UI.
     * @throws TaskMasterException If the index is out of range.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskMasterException {
        if (index <= 0 || index > tasks.getTasks().size()) {
            throw new TaskMasterException("Task index out of range.");
        }

        Task task = tasks.getTasks().get(index - 1);
        task.markAsDone();

        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new TaskMasterException("Failed to save task after marking as done.");
        }

        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Indicates that the mark command does not terminate the application.
     *
     * @return {@code false} since the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
