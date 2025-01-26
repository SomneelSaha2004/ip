package main;
import java.time.LocalDate;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone(){
        this.isDone =  true;
    }
    public void markAsNotDone(){
        this.isDone =  false;
    }
    public String getTaskDescription(){
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String save(){
        return ((isDone)?1:0) + "," + description;
    }

    public boolean isDue(LocalDate date){
        return false;
    }

}