package TaskMaster.Commands;
import TaskMaster.Storage.Storage;
import TaskMaster.Utils.TaskList;
import TaskMaster.Ui.Ui;
import TaskMaster.Tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Command to list tasks due on a specific date.
 */
public class AgendaCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs an AgendaCommand.
     *
     * @param date The date to list tasks for.
     */
    public AgendaCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> dueTasks = tasks.getTasksDueOn(date);
        if (dueTasks.isEmpty()) {
            ui.show("No tasks due on " + date + ".");
        } else {
            ui.show("Here are the tasks due on " + date + ":");
            for (Task task : dueTasks) {
                ui.show("  " + task);
            }
        }
    }
}
