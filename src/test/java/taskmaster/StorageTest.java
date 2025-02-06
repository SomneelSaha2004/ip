package taskmaster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskmaster.storage.Storage;
import taskmaster.tasks.Task;
import taskmaster.tasks.ToDo;

/**
 * Unit tests for the Storage class.
 */
public class StorageTest {

    private Path tempFile;
    private Storage storage;

    /**
     * Sets up a temporary file before each test.
     *
     * @throws IOException If an error occurs while creating the file.
     */
    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("taskmaster", ".txt");
        storage = new Storage(tempFile.toString());
    }

    /**
     * Cleans up the temporary file after each test.
     *
     * @throws IOException If an error occurs while deleting the file.
     */
    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    /**
     * Tests the load method when the file is empty.
     *
     * @throws IOException If an error occurs while loading the file.
     */
    @Test
    public void testLoadEmptyFile() throws IOException {
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Task list should be empty for an empty file.");
    }

    /**
     * Tests the save method by saving tasks to the file and verifying the contents.
     *
     * @throws IOException If an error occurs while saving the file.
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
     *
     * @throws IOException If an error occurs while loading the file.
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
        assertFalse(tasks.get(0).isCompleted());
        assertEquals("Complete assignment", tasks.get(1).getTaskDescription());
        assertTrue(tasks.get(1).isCompleted());
    }
}
