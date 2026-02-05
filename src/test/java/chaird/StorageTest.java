package chaird;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import chaird.exception.ChairdException;
import chaird.storage.Storage;
import chaird.task.Deadline;
import chaird.task.Task;
import chaird.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void readLineIntoTask_correctLines_correctTaskDescription() throws ChairdException {
        Storage storage = new Storage("test.txt");
        Task todo = storage.readLineIntoTask("T | 1 | read book");
        assertEquals("read book", todo.getDescription());
        assertTrue(todo.getIsDone());

    }

    @Test
    public void readLineIntoTask_correctLines_correctTaskDate() throws ChairdException {
        Storage storage = new Storage("test.txt");
        Deadline deadline = (Deadline) storage.readLineIntoTask("D | 0 | finish studying for test | 2025-02-25 1900");
        assertEquals("Feb 25 2025 1900", deadline.getBy().toString());
    }

    @Test
    public void save_load_cycle_allTasksSaved() throws ChairdException {
        String tempFile = "test.txt";
        Storage storage = new Storage(tempFile);

        ArrayList<Task> originalTasks = new ArrayList<>();
        originalTasks.add(new Todo("read book", false));
        originalTasks.add(new Deadline("study", true, "2025-02-25 1900"));

        storage.save(originalTasks);
        ArrayList<Task> loadedTasks = storage.load();

        boolean isDeleted = new File(tempFile).delete();

        assertEquals(2, loadedTasks.size());
        assertEquals("read book", ((Todo) loadedTasks.get(0)).getDescription());
        assertEquals("study", ((Deadline) loadedTasks.get(1)).getDescription());
        assertTrue(((Deadline) loadedTasks.get(1)).getIsDone());
    }

    @Test
    public void load_corruptedFile_throwsChairdExceptionThenReturnsEmptyList() {
        Storage storage = new Storage("corrupt.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter("corrupt.txt"))) {
            pw.println("D | 0 | CORRUPT | CORRUPT");
        } catch (IOException e) {
            fail("Failed to create corrupt test file: " + e.getMessage());
        }
        ArrayList<Task> tasks = storage.load();

        boolean isDeleted = new File("test_corrupt.txt").delete();
        assertTrue(tasks.isEmpty());
    }

}
