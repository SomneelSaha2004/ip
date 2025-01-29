package taskmaster.commands;

import taskmaster.exceptions.TaskMasterException;
import taskmaster.storage.Storage;
import taskmaster.tasks.Task;
import taskmaster.ui.Ui;
import taskmaster.utils.TaskList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws TaskMasterException If the index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskMasterException {
        if (index <= 0 || index > tasks.getTasks().size()) {
            throw new TaskMasterException("Task index out of range.");
        }
        Task removedTask = tasks.deleteTask(index - 1);
        ui.show("Noted. I've removed this task:");
        ui.show("  " + removedTask);
    }

    /**
     * Returns the index of the task to be deleted.
     *
     * @return The task index.
     */
    public int getIndex() {
        return index;
    }
}
