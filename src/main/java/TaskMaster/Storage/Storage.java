package TaskMaster.Storage;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import TaskMaster.Exceptions.TaskMasterException;
import TaskMaster.Parser.Parser;
import TaskMaster.Tasks.Task;

/**
 * Handles loading tasks from and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
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
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    tasks.add(Parser.parseTask(line));
                } catch (TaskMasterException e) {
                    System.err.println("Skipping invalid task in file: " + line);
                }
            }
        }

        return tasks;
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
