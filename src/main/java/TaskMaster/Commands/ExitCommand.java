package TaskMaster.Commands;
import TaskMaster.Storage.Storage;
import TaskMaster.Utils.TaskList;
import TaskMaster.Ui.Ui;


/**
 * Command to terminate the application.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Goodbye! Hope to see you again soon.");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
