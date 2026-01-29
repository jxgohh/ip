package chaird;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 * Manages storage of tasks to/from a text file.
 * Handles reading task data on startup and saving task during run.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all tasks from the storage file into an ArrayList
     * Creates the directory if it doesn't exist. Returns empty list for new or corrupted files..
     *
     * @return ArrayList of Task objects
     */
    public ArrayList<Task> load () {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        Path path = Paths.get("./src/main/data");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.out.println("Error creating directory: " + e.getMessage());
        }

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task taskToAdd = readLineIntoTask(line);
                if (taskToAdd == null) {
                    throw new ChairdException("Corrupted task data at line: " + line);
                }
                tasks.add(taskToAdd);
            }
        } catch (IOException | ChairdException e) {
            System.out.println("File is corrupted, please delete it under ./data/chaird.txt. Your list will be empty.");
            return new ArrayList<>();
        }
        return tasks;
    }

    /**
     * Saves the current task list to the storage file.
     * Overwrites existing file content with current tasks.
     *
     * @param tasks the ArrayList of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Task t: tasks) {
                pw.println(t.storageLine());
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    /**
     * Parses a single storage line into a Task object.
     * Supports Todo (T), Deadline (D), and Event (E) formats.
     *
     * @param line the line from the storage file
     * @return the reconstructed Task object
     * @throws ChairdException if the line format is corrupted
     */
    public Task readLineIntoTask(String line) throws ChairdException {
        String[] parts = line.split("\\|", 3);

        String eventType = parts[0].trim();
        String completed = parts[1].trim();
        boolean isDone = completed.equals("1");
        String description = parts[2].trim();

        if (eventType.equals("T")) {
            return new Todo(description, isDone);
        } else if (eventType.equals("D")) {
            String[] descParts = description.split("\\|", 2);
            return new Deadline(descParts[0].trim(), isDone, descParts[1].trim());
        } else if (eventType.equals("E")) {
            String[] descParts = description.split("\\|", 2);
            return new Event(descParts[0].trim(), isDone, descParts[1].trim());
        }

        return null;
    }
}
