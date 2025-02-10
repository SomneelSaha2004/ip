package taskmaster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import taskmaster.tasks.Event;

/**
 * Unit tests for the Event class.
 */
public class EventTest {

    private static final LocalDateTime TEST_START = LocalDateTime.of(2025, 1, 31, 10, 0);
    private static final LocalDateTime TEST_END = LocalDateTime.of(2025, 1, 31, 12, 0);
    private static final LocalDate CHECK_DATE = LocalDate.of(2025, 1, 31);

    /**
     * Tests the constructor that initializes an Event task as not done.
     */
    @Test
    public void testConstructorWithoutIsDone() {
        Event event = new Event("Team meeting", TEST_START, TEST_END);

        assertEquals("Team meeting", event.getTaskDescription());
        assertFalse(event.isCompleted());
        assertTrue(event.getTo().isEqual(TEST_END));
    }

    /**
     * Tests the constructor that initializes an Event task with a specified completion status.
     */
    @Test
    public void testConstructorWithIsDone() {
        Event event = new Event("Project presentation", true, TEST_START, TEST_END);

        assertEquals("Project presentation", event.getTaskDescription());
        assertTrue(event.isCompleted());
        assertTrue(event.isDue(TEST_START.toLocalDate()));
    }

    /**
     * Tests the toString method of the Event class.
     */
    @Test
    public void testToString() {
        Event event = new Event("Workshop", false, TEST_START, TEST_END);

        assertEquals("[E][ ] Workshop (from: 2025-01-31T10:00 to: 2025-01-31T12:00)", event.toString());

        event.markAsDone();
        assertEquals("[E][X] Workshop (from: 2025-01-31T10:00 to: 2025-01-31T12:00)", event.toString());
    }

    /**
     * Tests the save method of the Event class.
     */
    @Test
    public void testSave() {
        Event event = new Event("Team-building activity", false, TEST_START, TEST_END);

        assertEquals("E,0,Team-building activity,2025-01-31T10:00,2025-01-31T12:00", event.save());

        event.markAsDone();
        assertEquals("E,1,Team-building activity,2025-01-31T10:00,2025-01-31T12:00", event.save());
    }

    /**
     * Tests the isDue method of the Event class.
     */
    @Test
    public void testIsDue() {
        Event event = new Event("Client demo", false, TEST_START, TEST_END);

        assertTrue(event.isDue(CHECK_DATE));

        LocalDate futureDate = LocalDate.of(2025, 2, 1);
        assertFalse(event.isDue(futureDate));
    }
}
