package TaskMaster.Commands;
import TaskMaster.Storage.Storage;
import TaskMaster.Utils.TaskList;
import TaskMaster.Ui.Ui;
import TaskMaster.Exceptions.TaskMasterException;
import TaskMaster.Tasks.Task;
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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskMasterException {
        if (index <= 0 || index > tasks.getTasks().size()) {
            throw new TaskMasterException("Task index out of range.");
        }
        Task task = tasks.getTasks().get(index - 1);
        task.markAsNotDone();
        ui.show("Ok, I've marked this task as not done yet:");
        ui.show("  " + task);
    }
}
