package taskmaster.commands;

import java.time.LocalDate;
import java.util.List;

import taskmaster.storage.Storage;
import taskmaster.tasks.Task;
import taskmaster.ui.Ui;
import taskmaster.utils.TaskList;

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

    /**
     * Executes the command to list tasks due on a specific date.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage manager.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> dueTasks = tasks.getTasksDueOn(date);
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
