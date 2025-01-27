package TaskMaster.Commands;

import TaskMaster.Storage.Storage;
import TaskMaster.Utils.TaskList;
import TaskMaster.Ui.Ui;

/**
 * Command to display a list of all available commands and their usage.
 */
public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Here are the available commands:");
        ui.show("  list             - Lists all tasks");
        ui.show("  todo DESC        - Adds a to-do task (e.g., todo read book)");
        ui.show("  deadline DESC /by DATE_TIME");
        ui.show("                   - Adds a deadline task (e.g., deadline finish project /by 02/12/2019 1800)");
        ui.show("  event DESC /from START /to END");
        ui.show("                   - Adds an event task (e.g., event meeting /from 02/12/2019 0900 /to 02/12/2019 1100)");
        ui.show("  mark INDEX       - Marks task #INDEX as done (e.g., mark 3)");
        ui.show("  unmark INDEX     - Marks task #INDEX as not done (e.g., unmark 3)");
        ui.show("  delete INDEX     - Deletes task #INDEX (e.g., delete 2)");
        ui.show("  agenda DATE      - Lists tasks due on the specified date (e.g., agenda 02/12/2019)");
        ui.show("  help             - Displays this help message");
        ui.show("  bye              - Exits TaskMaster");
    }

}
