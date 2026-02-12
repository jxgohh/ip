package chaird.app;

import java.util.ArrayList;
import java.util.Scanner;

import chaird.exception.ChairdException;
import chaird.parser.Command;
import chaird.parser.Parser;
import chaird.storage.Storage;
import chaird.task.Deadline;
import chaird.task.Task;
import chaird.task.Event;
import chaird.task.Todo;
import chaird.tasklist.TaskList;
import chaird.ui.Ui;


/**
 * Chaird is the main driver of the application. Handles user interaction,
 * task storage, and command execution.
 *
 * @author jxgohh
 */
public class Chaird {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner sc;

    /**
     * Constructs a new Chaird instance by initializing
     * the user interface, storage system, scanner, and task list.
     *
     * @param filePath the path to the storage file where tasks are loaded from and saved to
     */
    public Chaird(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        sc = new Scanner(System.in);
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        ChairdException errorToPrint = null;
        try {
            Command cmd = Parser.parseLine(input);
            switch (cmd.getAction()) {
                case "bye":
                    return ui.goodbye();
                case "list":
                    return ui.printList(tasks.getList());
                case "mark":
                    tasks.mark(cmd.getIndex());
                    storage.save(tasks.getList());
                    return ui.mark(tasks.getList().get(cmd.getIndex() - 1));
                case "unmark":
                    tasks.unmark(cmd.getIndex());
                    storage.save(tasks.getList());
                    return ui.unmark(tasks.getList().get(cmd.getIndex() - 1));
                case "delete":
                    Task taskToDelete = tasks.getList().get(cmd.getIndex() - 1);
                    tasks.delete(cmd.getIndex());
                    return ui.delete(taskToDelete, tasks.size());
                case "todo":
                    Task newTodo = new Todo(cmd.getDesc());
                    tasks.add(newTodo);
                    storage.save(tasks.getList());
                    return ui.add(newTodo, tasks.size());
                case "deadline":
                    Task newDeadline = new Deadline(cmd.getDesc(), cmd.getDate());
                    tasks.add(newDeadline);
                    storage.save(tasks.getList());
                    return ui.add(newDeadline, tasks.size());
                case "event":
                    Task newEvent = new Event(cmd.getDesc(), cmd.getDate());
                    tasks.add(newEvent);
                    storage.save(tasks.getList());
                    return ui.add(newEvent, tasks.size());
                case "find":
                    ArrayList<Task> matchingTasks = tasks.findTasks(cmd.getDesc());
                    return ui.find(matchingTasks);
            }

        } catch (ChairdException e) {
            errorToPrint = e;
        }
        assert errorToPrint != null;
        return ui.printError(errorToPrint.getMessage());
    }
}
