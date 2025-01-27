package TaskMaster.Commands;
import TaskMaster.Storage.Storage;
import TaskMaster.Utils.TaskList;
import TaskMaster.Ui.Ui;
import TaskMaster.Exceptions.TaskMasterException;
import TaskMaster.Tasks.*;
import TaskMaster.Parser.Parser;
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

            case "deadline": {
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
                break;
            }

            case "event": {
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
                break;
            }

            default:
                throw new TaskMasterException("Unknown task type: " + taskType);
        }
    }
}
