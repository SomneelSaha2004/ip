package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;

/**
 * Command to terminate the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the application.
     *
     * @param tasks   The task list (not used in this command).
     * @param storage The storage manager (not used in this command).
     * @return A farewell message to display in the JavaFX UI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Goodbye! Hope to see you again soon.";
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
