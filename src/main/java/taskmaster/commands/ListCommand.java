package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks in the task list.
     *
     * @param tasks   The task list containing tasks.
     * @param storage The storage manager (not used in this command).
     * @return A string containing the formatted list of tasks for display in JavaFX.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            return "Your task list is empty!";
        } else {
            StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                response.append((i + 1)).append(". ").append(tasks.getTasks().get(i)).append("\n");
            }
            return response.toString().trim(); // Removing the trailing newline
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
