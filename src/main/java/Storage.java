import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load () {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        Path path = Paths.get("/src/main/data");
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
            System.out.println("Error! File is corrupted, please delete it under ./data/chaird.txt.");
            return new ArrayList<>();
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Task t: tasks) {
                pw.println(t.storageLine());
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
    public Task readLineIntoTask(String line) {
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
