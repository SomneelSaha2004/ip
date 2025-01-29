package taskmaster;

import java.io.IOException;
import java.util.ArrayList;
import taskmaster.commands.*;
import taskmaster.exceptions.TaskMasterException;
import taskmaster.parser.Parser;
import taskmaster.storage.Storage;
import taskmaster.ui.Ui;
import taskmaster.utils.TaskList;

/**
 * Main class for the TaskMaster application.
 */
public class TaskMaster {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a new TaskMaster application.
     */
    public TaskMaster() {
        this.ui = new Ui();
        this.storage = new Storage("data/taskmaster.txt");
        TaskList tempTasks;
        try {
            tempTasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Failed to load tasks. Starting with an empty list.");
            tempTasks = new TaskList(new ArrayList<>());
        }
        this.tasks = tempTasks;
    }

    /**
     * Runs the TaskMaster application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (TaskMasterException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point for the TaskMaster application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new TaskMaster().run();
    }
}
