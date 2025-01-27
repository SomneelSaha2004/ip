package TaskMaster;


import TaskMaster.Tasks.Task;
import TaskMaster.Tasks.ToDo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TaskMaster.Storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Storage class.
 */
public class StorageTest {
    private Path tempFile;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for testing
        tempFile = Files.createTempFile("taskmaster", ".txt");
        storage = new Storage(tempFile.toString());
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary file
        Files.deleteIfExists(tempFile);
    }

    /**
     * Tests the load method when the file is empty.
     */
    @Test
    public void testLoadEmptyFile() throws IOException {
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Task list should be empty for an empty file.");
    }

    /**
     * Tests the save method by saving tasks to the file and verifying the contents.
     */
    @Test
    public void testSave() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Read a book", false));
        tasks.add(new ToDo("Complete assignment", true));

        storage.save(tasks);

        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(2, lines.size());
        assertEquals("T,0,Read a book", lines.get(0));
        assertEquals("T,1,Complete assignment", lines.get(1));
    }

    /**
     * Tests the load method by saving tasks to the file and loading them back.
     */
    @Test
    public void testLoad() throws IOException {
        List<String> lines = List.of(
                "T,0,Read a book",
                "T,1,Complete assignment"
        );
        Files.write(tempFile, lines);

        ArrayList<Task> tasks = storage.load();
        assertEquals(2, tasks.size());
        assertEquals("Read a book", tasks.get(0).getTaskDescription());
        assertFalse(tasks.get(0).completed());
        assertEquals("Complete assignment", tasks.get(1).getTaskDescription());
        assertTrue(tasks.get(1).completed());
    }

    /**
     * Tests the load method when the file contains invalid tasks.
     */
    @Test
    public void testLoadWithInvalidTasks() throws IOException {
        List<String> lines = List.of(
                "T,0,Valid task",
                "INVALID LINE",
                "T,1,Another valid task"
        );
        Files.write(tempFile, lines);

        ArrayList<Task> tasks = storage.load();
        assertEquals(2, tasks.size());
        assertEquals("Valid task", tasks.get(0).getTaskDescription());
        assertEquals("Another valid task", tasks.get(1).getTaskDescription());
    }
}
