package taskmaster.commands;

import java.util.Arrays;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;
import taskmaster.tasks.Task;

/**
 * Command to find tasks containing any of the given keywords.
 */
public class FindCommand extends Command {
    private final String[] keywords;

    /**
     * Constructs a FindCommand.
     *
     * @param keywords The keywords to search for in task descriptions.
     */
    public FindCommand(String... keywords) {
        assert keywords.length > 0 : "FindCommand should have at least one keyword.";
        this.keywords = keywords;
    }

    /**
     * Executes the find command to search for tasks matching any of the keywords.
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
            boolean matches = Arrays.stream(keywords)
                    .allMatch(key -> task.getTaskDescription().toLowerCase().contains(key.toLowerCase()));

            if (matches) {
                response.append((++count)).append(". ").append(task).append("\n");
            }
        }

        if (count == 0) {
            return "No matching tasks found for keywords: " + String.join(", ", keywords);
        }

        return response.toString().trim();
    }
}
