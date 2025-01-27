package TaskMaster;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import TaskMaster.Tasks.ToDo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ToDo class.
 */
public class ToDoTest {

    /**
     * Tests the ToDo constructor that initializes the task as not done.
     */
    @Test
    public void testConstructorWithoutIsDone() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("Read a book", todo.getTaskDescription());
        assertFalse(todo.completed());
    }

    /**
     * Tests the ToDo constructor that allows specifying the completion status.
     */
    @Test
    public void testConstructorWithIsDone() {
        ToDo todo = new ToDo("Complete homework", true);
        assertEquals("Complete homework", todo.getTaskDescription());
        assertTrue(todo.completed());
    }

    /**
     * Tests the toString method of the ToDo class.
     */
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Finish project", false);
        assertEquals("[T][ ] Finish project", todo.toString());

        todo = new ToDo("Attend meeting", true);
        assertEquals("[T][X] Attend meeting", todo.toString());
    }

    /**
     * Tests the save method of the ToDo class.
     */
    @Test
    public void testSave() {
        ToDo todo = new ToDo("Write report", false);
        assertEquals("T,0,Write report", todo.save());

        todo = new ToDo("Submit assignment", true);
        assertEquals("T,1,Submit assignment", todo.save());
    }
}
