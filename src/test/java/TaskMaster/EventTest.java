package TaskMaster;
import TaskMaster.Tasks.Event;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class.
 */
public class EventTest {

    /**
     * Tests the constructor that initializes an Event task as not done.
     */
    @Test
    public void testConstructorWithoutIsDone() {
        LocalDateTime start = LocalDateTime.of(2025, 1, 31, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 31, 12, 0);
        Event event = new Event("Team meeting", start, end);

        assertEquals("Team meeting", event.getTaskDescription());
        assertFalse(event.completed());
        assertTrue(event.end().isEqual(end));

    }

    /**
     * Tests the constructor that initializes an Event task with a specified completion status.
     */
    @Test
    public void testConstructorWithIsDone() {
        LocalDateTime start = LocalDateTime.of(2025, 1, 31, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 31, 12, 0);
        Event event = new Event("Project presentation", true, start, end);

        assertEquals("Project presentation", event.getTaskDescription());
        assertTrue(event.completed());
        assertTrue(event.isDue(start.toLocalDate()));
    }

    /**
     * Tests the toString method of the Event class.
     */
    @Test
    public void testToString() {
        LocalDateTime start = LocalDateTime.of(2025, 1, 31, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 31, 12, 0);
        Event event = new Event("Workshop", false, start, end);

        assertEquals("[E][ ] Workshop (from: 2025-01-31T10:00 to: 2025-01-31T12:00)", event.toString());

        event.markAsDone();
        assertEquals("[E][X] Workshop (from: 2025-01-31T10:00 to: 2025-01-31T12:00)", event.toString());
    }

    /**
     * Tests the save method of the Event class.
     */
    @Test
    public void testSave() {
        LocalDateTime start = LocalDateTime.of(2025, 1, 31, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 31, 12, 0);
        Event event = new Event("Team-building activity", false, start, end);

        assertEquals("E,0,Team-building activity,2025-01-31T10:00,2025-01-31T12:00", event.save());

        event.markAsDone();
        assertEquals("E,1,Team-building activity,2025-01-31T10:00,2025-01-31T12:00", event.save());
    }

    /**
     * Tests the isDue method of the Event class.
     */
    @Test
    public void testIsDue() {
        LocalDateTime start = LocalDateTime.of(2025, 1, 31, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 31, 12, 0);
        Event event = new Event("Client demo", false, start, end);

        LocalDate checkDate = LocalDate.of(2025, 1, 31);
        assertTrue(event.isDue(checkDate));

        checkDate = LocalDate.of(2025, 2, 1);
        assertFalse(event.isDue(checkDate));
    }
}
