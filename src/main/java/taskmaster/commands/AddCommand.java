package taskmaster.commands;

import taskmaster.exceptions.TaskMasterException;
import taskmaster.parser.Parser;
import taskmaster.storage.Storage;
import taskmaster.tasks.Deadline;
import taskmaster.tasks.Event;
import taskmaster.tasks.ToDo;
import taskmaster.ui.Ui;
import taskmaster.utils.TaskList;

import java.time.LocalDateTime;

/**
 * Command to add a task (ToDo, Deadline, or Event).
 */
public class AddCommand extends Command {
    private final String taskType;
    private final String arguments;

    /**
     * Constructs an AddCommand.
     *
     * @param taskType  The type of task to add (todo, deadline, event).
     * @param arguments The arguments for the task (description, dates, etc.).
     */
    public AddCommand(String taskType, String arguments) {
        this.taskType = taskType;
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskMasterException {
        switch (taskType) {
            case "todo":
                if (arguments.isBlank()) {
                    throw new TaskMasterException("The description of a todo cannot be empty.");
                }
                tasks.addTask(new ToDo(arguments));
                ui.show("Got it. I've added this task:");
                ui.show("  " + tasks.getTasks().get(tasks.getTasks().size() - 1));
                break;

            case "deadline":
                handleDeadline(tasks, ui);
                break;

            case "event":
                handleEvent(tasks, ui);
                break;

            default:
                throw new TaskMasterException("Unknown task type: " + taskType);
        }
    }

    /**
     * Handles adding a deadline task.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     * @throws TaskMasterException If an error occurs while parsing the deadline.
     */
    private void handleDeadline(TaskList tasks, Ui ui) throws TaskMasterException {
        String[] parts = arguments.split("/by", 2);
        if (parts.length < 2) {
            throw new TaskMasterException("Please specify the deadline using '/by'.");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        LocalDateTime byDate = Parser.parseDateTime(by);
        tasks.addTask(new Deadline(description, byDate));
        ui.show("Got it. I've added this task:");
        ui.show("  " + tasks.getTasks().get(tasks.getTasks().size() - 1));
    }

    /**
     * Handles adding an event task.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     * @throws TaskMasterException If an error occurs while parsing the event time.
     */
    private void handleEvent(TaskList tasks, Ui ui) throws TaskMasterException {
        String[] parts = arguments.split("/from", 2);
        if (parts.length < 2) {
            throw new TaskMasterException("Please specify the event start time using '/from'.");
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2) {
            throw new TaskMasterException("Please specify the event end time using '/to'.");
        }
        LocalDateTime from = Parser.parseDateTime(timeParts[0].trim());
        LocalDateTime to = Parser.parseDateTime(timeParts[1].trim());
        tasks.addTask(new Event(description, from, to));
        ui.show("Got it. I've added this task:");
        ui.show("  " + tasks.getTasks().get(tasks.getTasks().size() - 1));
    }

    /**
     * Returns the type of task to add.
     *
     * @return The type of task to add.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Returns the arguments for the task.
     *
     * @return The arguments for the task.
     */
    public String getArguments() {
        return arguments;
    }
}
