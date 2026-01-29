package chaird;

import java.util.Scanner;

public class Chaird {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner sc;

    public Chaird(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        sc = new Scanner(System.in);
        tasks = new TaskList(storage.load());
    }

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
                }

            } catch (ChairdException e) {
                ui.printError(e.getMessage());
            }
        }
        sc.close();
    }
    public static void main(String[] args) {
        new Chaird("./src/main/data/chaird.txt").run();
    }
}
