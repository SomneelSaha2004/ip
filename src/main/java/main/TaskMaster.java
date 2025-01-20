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

        label:
        while(true){
            System.out.print("> ");
            String task = sc.nextLine();
            String[] s = task.split(" ");
            String command = s[0].trim();
            switch (command) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break label;
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {

                        System.out.println(i + 1 + ". " + tasks.get(i).toString());
                    }
                    break;
                case "mark": {
                    int index = Integer.parseInt(s[1].trim());
                    tasks.get(index - 1).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index - 1).toString());
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(s[1].trim());
                    tasks.get(index - 1).markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index - 1).toString());
                    break;
                }
                case "todo":
                    tasks.add(new ToDo(task));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  "+tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have "+tasks.size()+" tasks in the list.");
                    break;
                case "deadline":
                    String[] deadline = task.split("/by");
                    tasks.add(new Deadline(deadline[0].substring(8), deadline[1].trim()));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  "+tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have "+tasks.size()+" tasks in the list.");
                    break;
                case "event":

                    String[] event =  task.split("/");
                    String from  = event[1].split(" ")[1]+" "+event[1].split(" ")[2];
                    String to  = event[2].split(" ")[1];
                    tasks.add(new Event(event[0],from,to));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  "+tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have "+tasks.size()+" tasks in the list.");
                    break;
                default:
                    tasks.add(new Task(task));
                    System.out.println("added: " + task);
                    break;
            }
            System.out.println("____________________________________________________________");
        }
    }
}
