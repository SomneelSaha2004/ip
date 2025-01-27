package TaskMaster;

import TaskMaster.Commands.*;
import TaskMaster.Exceptions.TaskMasterException;
import TaskMaster.Tasks.Deadline;
import TaskMaster.Tasks.Event;
import TaskMaster.Tasks.Task;
import TaskMaster.Tasks.ToDo;
import org.junit.jupiter.api.Test;
import TaskMaster.Parser.Parser;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Parser class.
 */
public class ParserTest {

    /**
     * Tests parsing a valid "bye" command.
     */
    @Test
    public void testParseByeCommand() throws TaskMasterException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    /**
     * Tests parsing a valid "list" command.
     */
    @Test
    public void testParseListCommand() throws TaskMasterException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    /**
     * Tests parsing a valid "todo" command.
     */
    @Test
    public void testParseToDoCommand() throws TaskMasterException {
        Command command = Parser.parse("todo Read a book");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("todo", addCommand.getTaskType());
        assertEquals("Read a book", addCommand.getArguments());
    }

    /**
     * Tests parsing a valid "deadline" command.
     */
    @Test
    public void testParseDeadlineCommand() throws TaskMasterException {
        Command command = Parser.parse("deadline Submit assignment /by 20/1/2025 2359");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("deadline", addCommand.getTaskType());
        assertEquals("Submit assignment /by 20/1/2025 2359", addCommand.getArguments());
    }

    /**
     * Tests parsing a valid "event" command.
     */
    @Test
    public void testParseEventCommand() throws TaskMasterException {
        Command command = Parser.parse("event Meeting /from 20/1/2025 1000 /to 20/1/2025 1200");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("event", addCommand.getTaskType());
        assertEquals("Meeting /from 20/1/2025 1000 /to 20/1/2025 1200", addCommand.getArguments());
    }

    /**
     * Tests parsing an invalid command.
     */
    @Test
    public void testParseInvalidCommand() {
        assertThrows(TaskMasterException.class, () -> Parser.parse("invalidCommand"));
    }

    /**
     * Tests parsing a valid task line for a ToDo task.
     */
    @Test
    public void testParseTaskToDo() throws TaskMasterException {
        Task task = Parser.parseTask("T,0,Read a book");
        assertInstanceOf(ToDo.class, task);
        assertEquals("Read a book", task.getTaskDescription());
        assertFalse(task.completed());
    }

    /**
     * Tests parsing a valid task line for a Deadline task.
     */
    @Test
    public void testParseTaskDeadline() throws TaskMasterException {
        Task task = Parser.parseTask("D,1,Submit assignment,2025-01-20T23:59");
        assertInstanceOf(Deadline.class, task);
        assertEquals("Submit assignment", task.getTaskDescription());
        assertTrue(task.completed());
    }

    /**
     * Tests parsing a valid task line for an Event task.
     */
    @Test
    public void testParseTaskEvent() throws TaskMasterException {
        Task task = Parser.parseTask("E,0,Meeting,2025-01-20T10:00,2025-01-20T12:00");
        assertInstanceOf(Event.class, task);
        assertEquals("Meeting", task.getTaskDescription());
        assertFalse(task.completed());
    }

    /**
     * Tests parsing a valid date-time string.
     */
    @Test
    public void testParseValidDateTime() throws TaskMasterException {
        LocalDateTime dateTime = Parser.parseDateTime("20/1/2025 2359");
        assertEquals(LocalDateTime.of(2025, 1, 20, 23, 59), dateTime);
    }

    /**
     * Tests parsing an invalid date-time string.
     */
    @Test
    public void testParseInvalidDateTime() {
        assertThrows(TaskMasterException.class, () -> Parser.parseDateTime("invalidDate"));
    }
}
