package taskmaster.commands;

import taskmaster.exceptions.TaskMasterException;
import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;

/**
 * Abstract class representing a command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param storage The storage manager.
     * @return A response message as a string for display in the UI.
     * @throws TaskMasterException If there is an error during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws TaskMasterException;

    /**
     * Indicates whether this command will terminate the application.
     *
     * @return {@code true} if the application should exit; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
