package TaskMaster.Ui;
import TaskMaster.Utils.TaskList;
import TaskMaster.Tasks.Task;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance for interacting with the user.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        String logo = """
                ____________________________________________________________
                                
                Hello! I'm TaskMaster
                                
                What can I do for you?
                                
                ____________________________________________________________
                """;
        System.out.println(logo);
    }

    /**
     * Reads input from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    /**
     * Displays the given message to the user.
     *
     * @param message The message to display.
     */
    public void show(String message) {
        System.out.println(message);
    }

    /**
     * Displays a divider line for better readability.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays a generic error message when an unexpected issue occurs.
     */
    public void showGenericError() {
        System.out.println("An unexpected error occurred. Please try again.");
    }

    /**
     * Displays a formatted list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.getTasks().isEmpty()) {
            show("Your task list is empty!");
        } else {
            show("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                show((i + 1) + ". " + tasks.getTasks().get(i));
            }
        }
    }

    /**
     * Displays a message for a successfully added task.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        show("Got it. I've added this task:");
        show("  " + task);
        show("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message for a successfully removed task.
     *
     * @param task The task that was removed.
     * @param size The new size of the task list.
     */
    public void showTaskRemoved(Task task, int size) {
        show("Noted. I've removed this task:");
        show("  " + task);
        show("Now you have " + size + " tasks in the list.");
    }
}
