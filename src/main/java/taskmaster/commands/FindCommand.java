package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;
import taskmaster.tasks.Task;

/**
 * Command to find tasks containing a given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks matching the keyword.
     *
     * @param tasks   The task list to search.
     * @param storage The storage manager (not used in this command).
     * @return A string containing the search results for display in the JavaFX UI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder response = new StringBuilder();

        if (tasks.getTasks().isEmpty()) {
            return "Your task list is empty!";
        }

        response.append("Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            Task task = tasks.getTasks().get(i);
            if (task.getTaskDescription().toLowerCase().contains(keyword.toLowerCase())) {
                response.append((++count)).append(". ").append(task).append("\n");
            }
        }

        if (count == 0) {
            return "No matching tasks found for: " + keyword;
        }

        return response.toString().trim();
    }
}
