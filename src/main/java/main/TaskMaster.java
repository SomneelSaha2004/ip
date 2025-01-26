package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String dataPath  = "data/TaskMaster.txt";
        Scanner sc = new Scanner(System.in);

        StringBuilder text = new StringBuilder();
        try {
            java.io.File file = new java.io.File(dataPath);
            java.util.Scanner input = new java.util.Scanner(file);
            while (input.hasNext()) {
                text.append(input.nextLine()).append("\n");
            }
            input.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred while reading tasks from file.");
        }
        ArrayList<Task> tasks = readTasks(text.toString());

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
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

                        try {
                            LocalDateTime localDateTime = LocalDateTime.parse(deadlineBy, dtf);
                            tasks.add(new Deadline(deadlineDesc, localDateTime));
                        } catch (java.time.format.DateTimeParseException e){
                            throw new TaskMasterException("Please enter the deadline in the format 'dd/MM/yyyy HHmm'. e.g. '01/01/2021 1800'");
                            }

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
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

                        try {
                            LocalDateTime from = LocalDateTime.parse(fromTime, dtf);
                            LocalDateTime to = LocalDateTime.parse(toTime, dtf);
                            tasks.add(new Event(eventDesc, from, to));
                        } catch (java.time.format.DateTimeParseException e){
                            throw new TaskMasterException("Please enter the deadline in the format 'dd/MM/yyyy HHmm'. e.g. '01/01/2021 1800'");
                        }
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;
                    }
                    case "agenda":
                        if (remainder.isBlank()) {
                            throw new TaskMasterException("Please specify the date you want to see the agenda for.");
                        }

                        try {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate date = LocalDate.parse(remainder, dtf); // Parse as LocalDate

                            System.out.println("____________________________________________________________");
                            System.out.println("Here are the tasks on " + date.format(dtf) + ":");
                            boolean hasTasks = false;

                            for (Task task : tasks) {
                                if (task.isDue(date)) { // Use LocalDate comparison
                                    hasTasks = true;
                                    System.out.println("  " + task);
                                }
                            }

                            if (!hasTasks) {
                                System.out.println("No tasks on this date!");
                            }
                            break;
                        } catch (java.time.format.DateTimeParseException e) {
                            throw new TaskMasterException("Please enter the date in the format 'dd/MM/yyyy'. e.g. '01/01/2021'");
                        }


                    case "help":
                        // New 'help' command
                        System.out.println("____________________________________________________________");
                        System.out.println("Here are the available commands:");
                        System.out.println("  list             - Lists all tasks");
                        System.out.println("  todo DESC        - Adds a to-do task (e.g., todo read book)");
                        System.out.println("  deadline DESC /by DEADLINE");
                        System.out.println("                   - Adds a deadline task (e.g., deadline submit report /by 02/12/2019 1800)");
                        System.out.println("  event DESC /from START /to END");
                        System.out.println("                   - Adds an event task (e.g., event team meeting /from 02/12/2019 0900 /to 02/12/2019 1100)");
                        System.out.println("  mark INDEX       - Marks task #INDEX as done");
                        System.out.println("  unmark INDEX     - Marks task #INDEX as not done");
                        System.out.println("  delete INDEX     - Deletes task #INDEX");
                        System.out.println("  agenda DATE      - Lists all tasks due on the specified date (e.g., agenda 02/12/2019)");
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
                saveTasksToFile(tasks,dataPath);
                System.out.println("____________________________________________________________");
            }
        }
    }
    public static void saveTasksToFile(ArrayList<Task> tasks,String dataPath) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter(dataPath);
            for (Task task : tasks) {
                fw.write(task.save()+"\n");
            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }

    public static ArrayList<Task> readTasks(String text) {
        if (text.isBlank()) {
            return new ArrayList<>();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        String[] lines = text.split("\n");


        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        for (String line : lines) {
            String[] parts = line.split(",");
            String type = parts[0].trim();

            switch (type) {
                case "T":
                    boolean isDoneTodo = parts[1].trim().equals("1");
                    String todoDescription = parts[2].trim();
                    tasks.add(new ToDo(todoDescription, isDoneTodo));
                    break;

                case "D":
                    boolean isDoneDeadline = parts[1].trim().equals("1");
                    String deadlineDescription = parts[2].trim();
                    String deadlineDateTimeStr = parts[3].trim();
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDateTimeStr, dtf);
                    tasks.add(new Deadline(deadlineDescription, isDoneDeadline, deadlineDateTime));
                    break;

                case "E":
                    boolean isDoneEvent = parts[1].trim().equals("1");
                    String eventDescription = parts[2].trim();
                    String eventStartDateTimeStr = parts[3].trim();
                    String eventEndDateTimeStr = parts[4].trim();
                    LocalDateTime eventStart = LocalDateTime.parse(eventStartDateTimeStr, dtf);
                    LocalDateTime eventEnd = LocalDateTime.parse(eventEndDateTimeStr, dtf);
                    tasks.add(new Event(eventDescription, isDoneEvent, eventStart, eventEnd));
                    break;

                default:
                    System.out.println("Unknown task type: " + type);
                    break;
            }
        }

        return tasks;
    }

}

