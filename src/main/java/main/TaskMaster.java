package main;
import java.util.ArrayList;
import java.util.Scanner;
public class TaskMaster {
    public static void main(String[] args) {
        String logo = """
                ____________________________________________________________\
                
                Hello! I'm Taskmaster\
                
                What can I do for you?\
                
                ____________________________________________________________""";
        System.out.println(logo);
        Scanner sc =  new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        
        while(true){
            System.out.print("> ");
            String task = sc.nextLine();
            String[] s = task.split(" ");
            String command = s[0].trim();
            if (command.equals("bye")){
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if(command.equals("list")){
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++){
                    
                    System.out.println(i+1 + ". " + tasks.get(i).toString());
                }
            } else if(command.equals("mark")){
                int index =  Integer.parseInt(s[1].trim());
                tasks.get(index-1).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index-1).toString());
            }else if(command.equals("unmark")){
                int index =  Integer.parseInt(s[1].trim());
                tasks.get(index-1).markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(tasks.get(index-1).toString());
            }
            else{
            tasks.add(new Task(task));
            System.out.println("added: "+task);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
