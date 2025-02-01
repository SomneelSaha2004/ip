package taskmaster.commands;

import taskmaster.exceptions.TaskMasterException;
import taskmaster.parser.Parser;
import taskmaster.storage.Storage;
import taskmaster.tasks.Deadline;
import taskmaster.tasks.Event;
import taskmaster.tasks.ToDo;
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
    public String execute(TaskList tasks, Storage storage) throws TaskMasterException {
        switch (taskType) {
            case "todo":
                return handleToDo(tasks);
            case "deadline":
                return handleDeadline(tasks);
            case "event":
                return handleEvent(tasks);
            default:
                throw new TaskMasterException("Unknown task type: " + taskType);
        }
    }

    private String handleToDo(TaskList tasks) throws TaskMasterException {
        if (arguments.isBlank()) {
            throw new TaskMasterException("The description of a todo cannot be empty.");
        }
        ToDo todo = new ToDo(arguments);
        tasks.addTask(todo);
        return String.format("Got it. I've added this task:\n  %s", todo);
    }

    private String handleDeadline(TaskList tasks) throws TaskMasterException {
        String[] parts = arguments.split("/by", 2);
        if (parts.length < 2) {
            throw new TaskMasterException("Please specify the deadline using '/by'.");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        LocalDateTime byDate = Parser.parseDateTime(by);
        Deadline deadline = new Deadline(description, byDate);
        tasks.addTask(deadline);
        return String.format("Got it. I've added this task:\n  %s", deadline);
    }

    private String handleEvent(TaskList tasks) throws TaskMasterException {
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
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        return String.format("Got it. I've added this task:\n  %s", event);
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
