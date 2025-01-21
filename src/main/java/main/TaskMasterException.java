package main;

public class TaskMasterException extends Exception{
    public TaskMasterException(String message) {
        super("TaskMasterException : "+message);
    }
}
