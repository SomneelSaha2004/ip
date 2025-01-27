package TaskMaster.Commands;
import TaskMaster.Storage.Storage;
import TaskMaster.Utils.TaskList;
import TaskMaster.Ui.Ui;


/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            ui.show("Your task list is empty!");
        } else {
            ui.show("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                ui.show((i + 1) + ". " + tasks.getTasks().get(i));
            }
        }
    }
}
