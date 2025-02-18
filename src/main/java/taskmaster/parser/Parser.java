package taskmaster.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import taskmaster.commands.*;
import taskmaster.exceptions.TaskMasterException;
import taskmaster.tasks.*;

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
        assert fullCommand != null && !fullCommand.isBlank() : "Command cannot be null or empty.";

        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].trim();
        String arguments = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(commandWord, arguments);
            case "mark":
                return new MarkCommand(parseIndex(arguments, "mark"));
            case "unmark":
                return new UnmarkCommand(parseIndex(arguments, "unmark"));
            case "delete":
                return new DeleteCommand(parseIndex(arguments, "delete"));
            case "agenda":
                return new AgendaCommand(parseDate(arguments));
            case "help":
                return new HelpCommand();
            case "find":
                return new FindCommand(arguments.split("\\s+")); // Handle multiple keywords properly
            default:
                throw new TaskMasterException("❌ Unknown command: `" + commandWord + "`.\nType `help` for a list of commands.");
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
        return SUPPORTED_DATE_FORMATS.stream()
                .map(formatter -> tryParseDateTime(dateTimeString, formatter))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new TaskMasterException(
                        "❌ Invalid date format: `" + dateTimeString + "`.\n"
                                + "Supported formats: `d/M/yyyy HHmm`, `d-M-yyyy HHmm`, `yyyy-MM-dd'T'HH:mm`."
                ));
    }

    /**
     * Attempts to parse a date string with the given formatter.
     *
     * @param dateTimeString The date-time string to parse.
     * @param formatter The formatter to use.
     * @return An Optional containing LocalDateTime if parsing succeeds, or empty otherwise.
     */
    private static Optional<LocalDateTime> tryParseDateTime(String dateTimeString, DateTimeFormatter formatter) {
        try {
            return Optional.of(LocalDateTime.parse(dateTimeString, formatter));
        } catch (DateTimeParseException ignored) {
            return Optional.empty();
        }
    }

    /**
     * Parses a task line from the storage file into a Task object.
     *
     * @param line The line representing a task.
     * @return A Task object parsed from the line.
     * @throws TaskMasterException If the task cannot be parsed.
     */
    public static Task parseTask(String line) throws TaskMasterException {
        assert line != null && !line.isBlank() : "Task line should not be null or empty.";

        String[] parts = line.split(",", -1);
        if (parts.length < 3) {
            throw new TaskMasterException("❌ Malformed task data in storage file: `" + line + "`");
        }

        String type = parts[0].trim();
        boolean isDone = "1".equals(parts[1].trim());
        String description = parts[2].trim();

        switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                if (parts.length < 4) {
                    throw new TaskMasterException("❌ Missing deadline date for task: `" + description + "`");
                }
                return new Deadline(description, isDone, parseDateTime(parts[3].trim()));
            case "E":
                if (parts.length < 5) {
                    throw new TaskMasterException("❌ Missing event dates for task: `" + description + "`");
                }
                return new Event(description, isDone, parseDateTime(parts[3].trim()), parseDateTime(parts[4].trim()));
            default:
                throw new TaskMasterException("❌ Unknown task type in file: `" + type + "`");
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
        if (arguments.isBlank()) {
            throw new TaskMasterException("⚠️ `" + command + "` command requires a valid task number.");
        }
        try {
            int index = Integer.parseInt(arguments.trim());
            if (index <= 0) {
                throw new TaskMasterException("❌ Invalid task number. It must be a positive integer.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TaskMasterException("❌ Invalid task number: `" + arguments + "`. Please enter a number.");
        }
    }

    /**
     * Parses a date for commands like agenda.
     *
     *
     * @return A LocalDateTime object representing the date.
     * @throws TaskMasterException If the date is invalid.
     */
    public static LocalDate parseDate(String dateString) throws TaskMasterException {
        List<DateTimeFormatter> dateFormats = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy"),
                DateTimeFormatter.ISO_LOCAL_DATE
        );

        for (DateTimeFormatter formatter : dateFormats) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException ignored) {}
        }

        throw new TaskMasterException("❌ Invalid date format: `" + dateString + "`.\n"
                + "Supported formats: `d/M/yyyy`, `d-M-yyyy`, `yyyy-MM-dd`.");
    }

}
