package TaskMaster.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import TaskMaster.Commands.*;
import TaskMaster.Exceptions.TaskMasterException;
import TaskMaster.Tasks.*;

/**
 * Parses user commands and tasks from input.
 */
public class Parser {
    private static final List<DateTimeFormatter> SUPPORTED_DATE_FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
    );

    /**
     * Parses a user command into a Command object.
     *
     * @param fullCommand The full command entered by the user.
     * @return A Command object representing the parsed command.
     * @throws TaskMasterException If the command is invalid.
     */
    public static Command parse(String fullCommand) throws TaskMasterException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].trim();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new AddCommand("todo", arguments);
            case "deadline":
                return new AddCommand("deadline", arguments);
            case "event":
                return new AddCommand("event", arguments);
            case "mark": {
                int index = parseIndex(arguments, "mark");
                return new MarkCommand(index);
            }
            case "unmark": {
                int index = parseIndex(arguments, "unmark");
                return new UnmarkCommand(index);
            }
            case "delete": {
                int index = parseIndex(arguments, "delete");
                return new DeleteCommand(index);
            }
            case "agenda": {
                LocalDateTime date = parseDate(arguments);
                return new AgendaCommand(date.toLocalDate());
            }
            case "help": {
                return new HelpCommand();
            }
            case "exit": {
                return new ExitCommand();
            }
            case "find": {
                return new FindCommand(arguments);
            }
            default:
                throw new TaskMasterException("Unknown command: " + commandWord);
        }
    }

    /**
     * Parses a date-time string using supported formats.
     *
     * @param dateTimeString The date-time string to parse.
     * @return A LocalDateTime object parsed from the string.
     * @throws TaskMasterException If the string cannot be parsed into a valid date-time.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws TaskMasterException {
        for (DateTimeFormatter formatter : SUPPORTED_DATE_FORMATS) {
            try {
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }
        throw new TaskMasterException(
                "Invalid date format: " + dateTimeString +
                        ". Supported formats are: d/M/yyyy HHmm, d-M-yyyy HHmm, ISO_LOCAL_DATE_TIME."
        );
    }

    /**
     * Parses a task line from the storage file into a Task object.
     *
     * @param line The line representing a task.
     * @return A Task object parsed from the line.
     * @throws TaskMasterException If the task cannot be parsed.
     */
    public static Task parseTask(String line) throws TaskMasterException {
        String[] parts = line.split(",");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                LocalDateTime by = parseDateTime(parts[3].trim());
                return new Deadline(description, isDone, by);
            case "E":
                LocalDateTime from = parseDateTime(parts[3].trim());
                LocalDateTime to = parseDateTime(parts[4].trim());
                return new Event(description, isDone, from, to);
            default:
                throw new TaskMasterException("Unknown task type in file: " + type);
        }
    }

    /**
     * Parses the index for commands like mark, unmark, and delete.
     *
     * @param arguments The arguments passed with the command.
     * @param command   The command name for error messages.
     * @return The index parsed from the arguments.
     * @throws TaskMasterException If the index is invalid.
     */
    private static int parseIndex(String arguments, String command) throws TaskMasterException {
        try {
            return Integer.parseInt(arguments.trim());
        } catch (NumberFormatException e) {
            throw new TaskMasterException("Invalid index provided for " + command + " command. Please enter a valid number.");
        }
    }

    /**
     * Parses a date for commands like agenda.
     *
     * @param arguments The arguments passed with the command.
     * @return A LocalDateTime object representing the date.
     * @throws TaskMasterException If the date is invalid.
     */
    private static LocalDateTime parseDate(String arguments) throws TaskMasterException {
        try {
            return parseDateTime(arguments.trim());
        } catch (TaskMasterException e) {
            throw new TaskMasterException("Invalid date provided for agenda command. " + e.getMessage());
        }
    }
}
