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
    /**
     * Starts the Chaird application. This method:
     * displays a greeting message via the UI,
     * continuously reads user commands,
     * parses and executes user commands,
     * terminates when "bye" command is entered.
     */
    public void run() {
        ui.greet();
        while (true) {
            try {
                String line = ui.readCommand(sc);
                Command cmd = Parser.parseLine(line);
                if (cmd.getAction().equals("bye")) {
                    ui.goodbye();
                    break;
                } else if (cmd.getAction().equals("list")) {
                    ui.printList(tasks.getList());
                } else if (cmd.getAction().equals("mark")) {
                    tasks.mark(cmd.getInd());
                    ui.mark(tasks.getList().get(cmd.getInd()-1));
                    storage.save(tasks.getList());
                } else if (cmd.getAction().equals("unmark")) {
                    tasks.unmark(cmd.getInd());
                    ui.unmark(tasks.getList().get(cmd.getInd()-1));
                    storage.save(tasks.getList());
                } else if (cmd.getAction().equals("delete")) {
                    Task toDelete = tasks.getList().get(cmd.getInd()-1);
                    tasks.delete(cmd.getInd());
                    ui.delete(toDelete, tasks.size());
                } else if (cmd.getAction().equals("todo")) {
                    Task newTask = new Todo(cmd.getDesc());
                    tasks.add(newTask);
                    storage.save(tasks.getList());
                    ui.add(newTask, tasks.size());
                } else if (cmd.getAction().equals("deadline")) {
                    Task newTask = new Deadline(cmd.getDesc(), cmd.getDate());
                    tasks.add(newTask);
                    storage.save(tasks.getList());
                    ui.add(newTask, tasks.size());
                } else if (cmd.getAction().equals("event")) {
                    Task newTask = new Event(cmd.getDesc(), cmd.getDate());
                    tasks.add(newTask);
                    storage.save(tasks.getList());
                    ui.add(newTask, tasks.size());
                } else if (cmd.getAction().equals("find")) {
                    ArrayList<Task> matchingTasks = tasks.findTasks(cmd.getDesc());
                    ui.find(matchingTasks);
                }

            } catch (ChairdException e) {
                ui.printError(e.getMessage());
            }
        }
        sc.close();
    }
}
