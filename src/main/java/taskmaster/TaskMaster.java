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
    private String commandType;

    /**
     * Constructs a new TaskMaster application.
     */
    public TaskMaster() {
        String filePath = System.getenv().getOrDefault("TASKMASTER_STORAGE", "data/taskmaster.txt");
        this.storage = new Storage(filePath);
        TaskList tempTasks;
        try {
            tempTasks = new TaskList(storage.load());
        } catch (IOException e) {
            tempTasks = new TaskList(new ArrayList<>());
        }
        this.tasks = tempTasks;
    }

    /**
     * Processes user input and returns the response for JavaFX UI,
     * while saving any changes to storage.
     *
     * @param input The user's input command.
     * @return The response generated after executing the command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            assert command != null : "Parser should never return a null command.";
            commandType = command.getClass().getSimpleName();
            String response = command.execute(tasks, storage);

            // Write changes to storage after successful command execution
            storage.save(tasks.getTasks());
            if(command.isExit()){
                System.exit(0);
            }
            return "My glorious king\n"+response;
        } catch (TaskMasterException e) {
            return "Error: " + e.getMessage();
        } catch (IOException ioException) {
            return "Error saving tasks: " + ioException.getMessage();
        }
    }

    /**
     * Returns the last executed command type.
     *
     * @return The command type.
     */
    public String getCommandType() {
        return commandType;
    }
}
