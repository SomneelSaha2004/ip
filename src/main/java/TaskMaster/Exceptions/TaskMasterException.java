package TaskMaster.Exceptions;

/**
 * Represents a custom exception specific to the TaskMaster application.
 *
 * This exception is thrown when an error related to task management occurs,
 * such as invalid user input or an operation that cannot be completed.
 */
public class TaskMasterException extends Exception {

    /**
     * Constructs a new TaskMasterException with the specified detail message.
     *
     * @param message The detail message providing more information about the error.
     *                This message is prepended with "TaskMasterException : " for clarity.
     */
    public TaskMasterException(String message) {
        super("TaskMasterException : " + message);
    }
}
