package taskmaster.commands;

import java.time.LocalDateTime;

import taskmaster.exceptions.TaskMasterException;
import taskmaster.parser.Parser;
import taskmaster.storage.Storage;
import taskmaster.tasks.Deadline;
import taskmaster.tasks.Event;
import taskmaster.tasks.ToDo;
import taskmaster.utils.TaskList;

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
        switch (taskType.toLowerCase()) {
            case "todo" -> {
                return handleToDo(tasks);
            }
            case "deadline" -> {
                return handleDeadline(tasks);
            }
            case "event" -> {
                return handleEvent(tasks);
            }
            default -> throw new TaskMasterException(
                    "Unknown task type: %s. Valid types: todo, deadline, event."
                            .formatted(taskType)
            );
        }
    }

    /**
     * Handles adding a ToDo task.
     */
    private String handleToDo(TaskList tasks) throws TaskMasterException {
        if (arguments.isBlank()) {
            throw new TaskMasterException(
                    "❌ Error: The description of a todo cannot be empty.\n"
                            + "Usage: todo <task description>"
            );
        }
        ToDo todo = new ToDo(arguments);
        tasks.addTask(todo);
        return formatTaskResponse(todo);
    }

    /**
     * Handles adding a Deadline task.
     */
    private String handleDeadline(TaskList tasks) throws TaskMasterException {
        String[] parts = splitInput(arguments, "/by",
                "deadline <task> /by <deadline>"
        );
        LocalDateTime byDate = Parser.parseDateTime(parts[1]);
        Deadline deadline = new Deadline(parts[0], byDate);
        tasks.addTask(deadline);
        return formatTaskResponse(deadline);
    }

    /**
     * Handles adding an Event task.
     */
    private String handleEvent(TaskList tasks) throws TaskMasterException {
        String[] parts = splitInput(arguments, "/from",
                "event <task> /from <start> /to <end>"
        );
        String[] timeParts = splitInput(parts[1], "/to",
                "event <task> /from <start> /to <end>"
        );
        LocalDateTime from = Parser.parseDateTime(timeParts[0]);
        LocalDateTime to = Parser.parseDateTime(timeParts[1]);

        Event event = new Event(parts[0], from, to);
        tasks.addTask(event);
        return formatTaskResponse(event);
    }

    /**
     * Extracts the command arguments based on a delimiter.
     *
     * @param input The raw input string.
     * @param delimiter The delimiter used to split the input.
     * @param errorMessage The error message if the format is incorrect.
     * @return A string array with the split values.
     * @throws TaskMasterException if the format is invalid.
     */
    private String[] splitInput(String input, String delimiter, String errorMessage)
            throws TaskMasterException {
        String[] parts = input.split(delimiter, 2);
        if (parts.length < 2) {
            throw new TaskMasterException(
                    "❌ Error: Invalid format.\nUsage: " + errorMessage
            );
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    /**
     * Formats a task addition response.
     *
     * @param task The task added.
     * @return The formatted response string.
     */
    private String formatTaskResponse(Object task) {
        return "✅ Task added successfully:\n  %s".formatted(task);
    }

    public String getTaskType() {
        return taskType;
    }

    public String getArguments() {
        return arguments;
    }
}
