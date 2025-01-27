package TaskMaster.Commands;
import TaskMaster.Storage.Storage;
import TaskMaster.Utils.TaskList;
import TaskMaster.Ui.Ui;
import TaskMaster.Exceptions.TaskMasterException;
import TaskMaster.Tasks.Task;
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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskMasterException {
        if (index <= 0 || index > tasks.getTasks().size()) {
            throw new TaskMasterException("Task index out of range.");
        }
        Task removedTask = tasks.deleteTask(index - 1);
        ui.show("Noted. I've removed this task:");
        ui.show("  " + removedTask);
    }
}
