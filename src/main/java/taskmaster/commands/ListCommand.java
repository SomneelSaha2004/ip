package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;
import taskmaster.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks in the task list.
     *
     * @param tasks   The task list containing tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage manager (not used in this command).
     */
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

    /**
     * Indicates that the list command does not terminate the application.
     *
     * @return {@code false} since the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
