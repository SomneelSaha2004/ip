package taskmaster;

import java.io.IOException;
import java.util.ArrayList;
import taskmaster.commands.Command;
import taskmaster.exceptions.TaskMasterException;
import taskmaster.parser.Parser;
import taskmaster.storage.Storage;
import taskmaster.utils.TaskList;

/**
 * Main class for the TaskMaster application.
 */
public class TaskMaster {
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a new TaskMaster application.
     */
    public TaskMaster() {
        this.storage = new Storage("data/taskmaster.txt");
        TaskList tempTasks;
        try {
            tempTasks = new TaskList(storage.load());
        } catch (IOException e) {
            tempTasks = new TaskList(new ArrayList<>());
        }
        this.tasks = tempTasks;
    }

    /**
     * Processes user input and returns the response for JavaFX UI.
     *
     * @param input The user's input command.
     * @return The response generated after executing the command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (TaskMasterException e) {
            return "Error: " + e.getMessage();
        }
    }
}
