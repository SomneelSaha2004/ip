package TaskMaster.Utils;

import java.time.LocalDate;
import java.util.ArrayList;

import TaskMaster.Tasks.Task;

/**
 * Represents the list of tasks and provides operations to manage it.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Filters tasks due on a specific date.
     *
     * @param date The date to filter tasks by.
     * @return A list of tasks due on the specified date.
     */
    public ArrayList<Task> getTasksDueOn(LocalDate date) {
        ArrayList<Task> dueTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isDue(date)) {
                dueTasks.add(task);
            }
        }
        return dueTasks;
    }
}
