package main;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task{
    protected LocalDateTime by;
    public Deadline(String description,LocalDateTime by){
        super(description);
        this.by =  by;
    }
    public Deadline(String description,boolean isDone,LocalDateTime by){
        super(description,isDone);
        this.by = by;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by.toString() + ")";
    }

    @Override
    public String save(){
        return "D," + super.save() + "," + by.toString();
    }
    @Override
    public boolean isDue(LocalDate date){
        return by.toLocalDate().isEqual(date);
    }
}
