package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;
import taskmaster.ui.Ui;

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
     * @param ui      The user interface for displaying results.
     * @param storage The storage manager (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            ui.show("Your task list is empty!");
            return;
        }

        ui.show("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            if (tasks.getTasks().get(i).getTaskDescription().contains(keyword)) {
                ui.show((i + 1) + ". " + tasks.getTasks().get(i));
            }
        }
    }
}
