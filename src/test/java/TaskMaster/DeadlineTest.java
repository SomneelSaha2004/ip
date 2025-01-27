package TaskMaster;

import org.junit.jupiter.api.Test;
import TaskMaster.Tasks.Deadline;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the constructor that initializes a Deadline task as not done.
     */
    @Test
    public void testConstructorWithoutIsDone() {
        LocalDateTime dueDate = LocalDateTime.of(2025, 1, 31, 23, 59);
        Deadline deadline = new Deadline("Submit assignment", dueDate);

        assertEquals("Submit assignment", deadline.getTaskDescription());
        assertFalse(deadline.completed());
        assertTrue(deadline.isDue(dueDate.toLocalDate()));
    }

    /**
     * Tests the constructor that initializes a Deadline task with a specified completion status.
     */
    @Test
    public void testConstructorWithIsDone() {
        LocalDateTime dueDate = LocalDateTime.of(2025, 1, 31, 23, 59);
        Deadline deadline = new Deadline("Complete project", true, dueDate);

        assertEquals("Complete project", deadline.getTaskDescription());
        assertTrue(deadline.completed());
        assertTrue(deadline.isDue(dueDate.toLocalDate()));
    }

    /**
     * Tests the toString method of the Deadline class.
     */
    @Test
    public void testToString() {
        LocalDateTime dueDate = LocalDateTime.of(2025, 1, 31, 23, 59);
        Deadline deadline = new Deadline("Prepare presentation", false, dueDate);

        assertEquals("[D][ ] Prepare presentation (by: 2025-01-31T23:59)", deadline.toString());

        deadline.markAsDone();
        assertEquals("[D][X] Prepare presentation (by: 2025-01-31T23:59)", deadline.toString());
    }

    /**
     * Tests the save method of the Deadline class.
     */
    @Test
    public void testSave() {
        LocalDateTime dueDate = LocalDateTime.of(2025, 1, 31, 23, 59);
        Deadline deadline = new Deadline("Study for exams", false, dueDate);

        assertEquals("D,0,Study for exams,2025-01-31T23:59", deadline.save());

        deadline.markAsDone();
        assertEquals("D,1,Study for exams,2025-01-31T23:59", deadline.save());
    }

    /**
     * Tests the isDue method of the Deadline class.
     */
    @Test
    public void testIsDue() {
        LocalDateTime dueDate = LocalDateTime.of(2025, 1, 31, 23, 59);
        Deadline deadline = new Deadline("Submit report", false, dueDate);

        LocalDate checkDate = LocalDate.of(2025, 1, 31);
        assertTrue(deadline.isDue(checkDate));

        checkDate = LocalDate.of(2025, 2, 1);
        assertFalse(deadline.isDue(checkDate));
    }
}
