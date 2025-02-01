package taskmaster.commands;

import java.time.LocalDate;
import java.util.List;

import taskmaster.storage.Storage;
import taskmaster.tasks.Task;
import taskmaster.utils.TaskList;

/**
 * Command to list tasks due on a specific date.
 */
public class AgendaCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs an AgendaCommand.
     *
     * @param date The date to list tasks for.
     */
    public AgendaCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command to list tasks due on a specific date.
     *
     * @param tasks   The task list.
     * @param storage The storage manager.
     * @return A response message containing the tasks due on the specified date.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Task> dueTasks = tasks.getTasksDueOn(date);
        StringBuilder response = new StringBuilder();

        if (dueTasks.isEmpty()) {
            response.append("No tasks due on ").append(date).append(".");
        } else {
            response.append("Here are the tasks due on ").append(date).append(":\n");
            for (int i = 0; i < dueTasks.size(); i++) {
                response.append(i + 1).append(". ").append(dueTasks.get(i)).append("\n");
            }
        }
        return response.toString().trim();
    }
}
