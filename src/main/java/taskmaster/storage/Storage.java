package taskmaster.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import taskmaster.exceptions.TaskMasterException;
import taskmaster.parser.Parser;
import taskmaster.tasks.Task;

/**
 * Handles loading tasks from and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new storage instance with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("Created new task storage file: " + filePath);
            }
        }

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines
                    .map(line -> {
                        try {
                            return Parser.parseTask(line);
                        } catch (TaskMasterException e) {
                            System.err.println("Skipping invalid task in file: " + line);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull) // Remove invalid tasks
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.save() + "\n");
            }
        }
    }
}

