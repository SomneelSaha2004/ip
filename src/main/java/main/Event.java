package main;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime from,to;
    public Event(String description,LocalDateTime from,LocalDateTime to){
        super(description);
        this.from =  from;
        this.to =  to;
    }
    public Event(
        String description,
        boolean isDone,
        LocalDateTime from,
        LocalDateTime to
    ){
        super(description,isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]"+super.toString() + " (from: "+from+" to: "+to+")";
    }
    @Override
    public String save(){
        return "E," + super.save() + "," + from + "," + to;
    }
    @Override
    public boolean isDue(LocalDate date){
        return from.toLocalDate().isEqual(date);
    }
}
