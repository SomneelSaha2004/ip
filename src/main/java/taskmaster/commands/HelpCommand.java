package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;
import taskmaster.ui.Ui;

/**
 * Command to display a list of all available commands and their usage.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command to display all available commands.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage manager (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Here are the available commands:");
        ui.show("  list             - Lists all tasks");
        ui.show("  todo DESC        - Adds a to-do task (e.g., todo read book)");
        ui.show("  deadline DESC /by DATE_TIME");
        ui.show("                   - Adds a deadline task");
        ui.show("                     (e.g., deadline finish project /by 02/12/2019 1800)");
        ui.show("  event DESC /from START /to END");
        ui.show("                   - Adds an event task");
        ui.show("                     (e.g., event meeting /from 02/12/2019 0900 /to 02/12/2019 1100)");
        ui.show("  mark INDEX       - Marks task #INDEX as done (e.g., mark 3)");
        ui.show("  unmark INDEX     - Marks task #INDEX as not done (e.g., unmark 3)");
        ui.show("  delete INDEX     - Deletes task #INDEX (e.g., delete 2)");
        ui.show("  agenda DATE      - Lists tasks due on the specified date");
        ui.show("                     (e.g., agenda 02/12/2019)");
        ui.show("  help             - Displays this help message");
        ui.show("  bye              - Exits taskmaster");
    }

    /**
     * Indicates that the help command does not terminate the application.
     *
     * @return {@code false} since the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
