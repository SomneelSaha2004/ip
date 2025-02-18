package taskmaster.commands;

import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;

/**
 * Command to display a list of all available commands and their usage.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command to display all available commands.
     *
     * @param tasks   The task list (not used in this command).
     * @param storage The storage manager (not used in this command).
     * @return A string containing the help message for display in the JavaFX UI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return """
            +---------------------------------------------------+
            |              TaskMaster Command Guide             |
            +---------------------------------------------------+

            TASK MANAGEMENT COMMANDS:
            -----------------------------------------------------
            [*] list               - Lists all tasks.
            [*] todo DESC          - Adds a to-do task.  
                (e.g., todo Read book)
            [*] deadline DESC /by DATE_TIME  
                - Adds a deadline task.  
                (e.g., deadline Submit report /by 02/12/2019 1800)
            [*] event DESC /from START /to END  
                - Adds an event task.  
                (e.g., event Meeting /from 02/12/2019 0900 /to 02/12/2019 1100)

            TASK STATUS & MODIFICATION:
            -----------------------------------------------------
            [*] mark INDEX         - Marks task as done.  
                (e.g., mark 3)
            [*] unmark INDEX       - Marks task as not done.  
                (e.g., unmark 3)
            [*] delete INDEX       - Deletes a task.  
                (e.g., delete 2)

            SCHEDULE & SEARCH:
            -----------------------------------------------------
            [*] agenda DATE        - Lists tasks due on a date.  
                (e.g., agenda 02/12/2019)
            [*] find KEYWORD       - Finds tasks containing a keyword.  
                (e.g., find book)

            OTHER COMMANDS:
            -----------------------------------------------------
            [*] help               - Displays this help page.
            [*] bye                - Exits TaskMaster.

            -----------------------------------------------------
            TIP: Use 'find' to quickly search your tasks!
            """;
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
