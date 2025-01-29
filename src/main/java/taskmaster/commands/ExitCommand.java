package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;
import taskmaster.ui.Ui;

/**
 * Command to terminate the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the application.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage manager (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Goodbye! Hope to see you again soon.");
    }

    /**
     * Indicates that this command terminates the application.
     *
     * @return {@code true} since this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
