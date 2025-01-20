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

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        label:
        while (true) {
            System.out.print("> ");
            String userInput = sc.nextLine().trim();


            String[] parts = userInput.split(" ", 2);
            String command = parts[0].trim();

            String remainder = (parts.length > 1) ? parts[1].trim() : "";

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
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                    break;

                case "mark": {

                    int index = Integer.parseInt(remainder);
                    tasks.get(index - 1).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index - 1));
                    break;
                }

                case "unmark": {

                    int index = Integer.parseInt(remainder);
                    tasks.get(index - 1).markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index - 1));
                    break;
                }

                case "todo":

                    tasks.add(new ToDo(remainder));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;

                case "deadline":

                    String[] deadlineParts = remainder.split("/by", 2);
                    String deadlineDesc = deadlineParts[0].trim();
                    String deadlineBy = (deadlineParts.length > 1) ? deadlineParts[1].trim() : "";
                    tasks.add(new Deadline(deadlineDesc, deadlineBy));

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;

                case "event":

                    String[] fromSplit = remainder.split("/from", 2);
                    String eventDesc = fromSplit[0].trim();
                    String fromAndTo = (fromSplit.length > 1) ? fromSplit[1].trim() : "";


                    String[] toSplit = fromAndTo.split("/to", 2);
                    String fromTime = toSplit[0].trim();
                    String toTime = (toSplit.length > 1) ? toSplit[1].trim() : "";

                    tasks.add(new Event(eventDesc, fromTime, toTime));

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;

                default:

                    tasks.add(new Task(userInput));
                    System.out.println("added: " + userInput);
                    break;
            }

            System.out.println("____________________________________________________________");
        }
    }
}
