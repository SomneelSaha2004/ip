package TaskMaster.Commands;
import TaskMaster.Storage.Storage;
import TaskMaster.Utils.TaskList;
import TaskMaster.Ui.Ui;
import TaskMaster.Exceptions.TaskMasterException;
/**
 * Abstract class representing a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws TaskMasterException If there is an error during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TaskMasterException;

    /**
     * Indicates whether this command will terminate the application.
     *
     * @return {@code true} if the application should exit; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
