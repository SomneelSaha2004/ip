package main;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskMaster {
    public static void main(String[] args) throws TaskMasterException {
        String logo = """
                ____________________________________________________________
                
                Hello! I'm Taskmaster
                
                What can I do for you?
                
                ____________________________________________________________""";
        System.out.println(logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        label:
        while (true) {
            System.out.print("> ");
            String userInput = sc.nextLine().trim();

            // If user just presses Enter (empty command), skip
            if (userInput.isBlank()) {
                System.out.println("____________________________________________________________");
                System.out.println("No command entered. Type 'help' to see available commands.");
                System.out.println("____________________________________________________________");
                continue;
            }

            String[] parts = userInput.split(" ", 2);
            String command = parts[0].trim();
            String remainder = (parts.length > 1) ? parts[1].trim() : "";

            try {
                switch (command) {
                    case "bye":
                        System.out.println("____________________________________________________________");
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        break label;

                    case "list":
                        System.out.println("____________________________________________________________");
                        if (tasks.isEmpty()) {
                            System.out.println("Your task list is empty!");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + "." + tasks.get(i));
                            }
                        }
                        break;

                    case "mark": {
                        if (remainder.isBlank()) {
                            throw new TaskMasterException("Please specify the task number to mark as done.");
                        }
                        int index;
                        try {
                            index = Integer.parseInt(remainder);
                        } catch (NumberFormatException e) {
                            throw new TaskMasterException("Invalid task number. Please enter a valid integer.");
                        }
                        if (index > tasks.size() || index < 1) {
                            throw new TaskMasterException("Task not found.");
                        }
                        tasks.get(index - 1).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(index - 1));
                        break;
                    }

                    case "unmark": {
                        if (remainder.isBlank()) {
                            throw new TaskMasterException("Please specify the task number to unmark.");
                        }
                        int index;
                        try {
                            index = Integer.parseInt(remainder);
                        } catch (NumberFormatException e) {
                            throw new TaskMasterException("Invalid task number. Please enter a valid integer.");
                        }
                        if (index > tasks.size() || index < 1) {
                            throw new TaskMasterException("Task not found.");
                        }
                        tasks.get(index - 1).markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Ok, I've marked this task as not done yet:");
                        System.out.println(tasks.get(index - 1));
                        break;
                    }

                    case "delete": {
                        // New 'delete' command
                        if (remainder.isBlank()) {
                            throw new TaskMasterException("Please specify the task number to delete.");
                        }
                        int index;
                        try {
                            index = Integer.parseInt(remainder);
                        } catch (NumberFormatException e) {
                            throw new TaskMasterException("Invalid task number. Please enter a valid integer.");
                        }
                        if (index > tasks.size() || index < 1) {
                            throw new TaskMasterException("Task not found.");
                        }
                        Task removed = tasks.remove(index - 1);
                        System.out.println("____________________________________________________________");
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + removed);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;
                    }

                    case "todo":
                        if (remainder.isBlank()) {
                            throw new TaskMasterException("The description of a todo cannot be empty.");
                        }
                        tasks.add(new ToDo(remainder));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "deadline": {
                        if (remainder.isBlank()) {
                            throw new TaskMasterException("The description of a deadline cannot be empty.");
                        }
                        String[] deadlineParts = remainder.split("/by", 2);
                        if (deadlineParts.length < 2) {
                            throw new TaskMasterException("Please specify the deadline using '/by'. e.g. 'deadline finish project /by Monday'");
                        }
                        String deadlineDesc = deadlineParts[0].trim();
                        String deadlineBy = deadlineParts[1].trim();

                        if (deadlineDesc.isBlank()) {
                            throw new TaskMasterException("The description of a deadline cannot be empty.");
                        }
                        if (deadlineBy.isBlank()) {
                            throw new TaskMasterException("The 'by' part of a deadline cannot be empty.");
                        }

                        tasks.add(new Deadline(deadlineDesc, deadlineBy));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;
                    }

                    case "event": {
                        if (remainder.isBlank()) {
                            throw new TaskMasterException("The description of an event cannot be empty.");
                        }

                        String[] fromSplit = remainder.split("/from", 2);
                        if (fromSplit.length < 2) {
                            throw new TaskMasterException("Please specify the event start time using '/from'. e.g. 'event party /from Monday /to Tuesday'");
                        }
                        String eventDesc = fromSplit[0].trim();
                        String fromAndTo = fromSplit[1].trim();

                        if (eventDesc.isBlank()) {
                            throw new TaskMasterException("The description of an event cannot be empty.");
                        }

                        String[] toSplit = fromAndTo.split("/to", 2);
                        if (toSplit.length < 2) {
                            throw new TaskMasterException("Please specify the event end time using '/to'.");
                        }
                        String fromTime = toSplit[0].trim();
                        String toTime = toSplit[1].trim();

                        if (fromTime.isBlank()) {
                            throw new TaskMasterException("Please specify a valid start time for the event.");
                        }
                        if (toTime.isBlank()) {
                            throw new TaskMasterException("Please specify a valid end time for the event.");
                        }

                        tasks.add(new Event(eventDesc, fromTime, toTime));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;
                    }

                    case "help":
                        // New 'help' command
                        System.out.println("____________________________________________________________");
                        System.out.println("Here are the available commands:");
                        System.out.println("  list             - Lists all tasks");
                        System.out.println("  todo DESC        - Adds a to-do task (e.g., todo read book)");
                        System.out.println("  deadline DESC /by DEADLINE");
                        System.out.println("                   - Adds a deadline task");
                        System.out.println("  event DESC /from START /to END");
                        System.out.println("                   - Adds an event task");
                        System.out.println("  mark INDEX       - Marks task #INDEX as done");
                        System.out.println("  unmark INDEX     - Marks task #INDEX as not done");
                        System.out.println("  delete INDEX     - Deletes task #INDEX");
                        System.out.println("  help             - Shows this help message");
                        System.out.println("  bye              - Exits TaskMaster");
                        break;

                    default:
                        throw new TaskMasterException("I'm sorry, but I don't know what that means. Type 'help' for available commands.");
                }
            } catch (TaskMasterException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
            } finally {
                System.out.println("____________________________________________________________");
            }
        }
    }
}
