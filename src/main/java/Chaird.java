import java.util.ArrayList;
import java.util.Scanner;

public class Chaird {
    private final static String LINE = "---------------------------------------------------------------------------------------------";
    private static Storage storage = new Storage("src/main/data/chaird.txt");
    private static ArrayList<Task> list = storage.load();

    private static void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Chaird (chat)\nWhat can I do for you?");
        System.out.println(LINE);
    }

    private static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void append(Task t) {
         list.add(t);
    }

    private static void mark(int ind) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        Task currTask = list.get(ind-1);
        currTask.mark();
        System.out.println(currTask);
        System.out.println(LINE);
    }

    private static void unmark(int ind) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        Task currTask = list.get(ind-1);
        currTask.unmark();
        System.out.println(currTask);
        System.out.println(LINE);
    }

    private static void delete(int ind) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        Task currTask = list.get(ind-1);
        System.out.println("    " + currTask);
        list.remove(ind-1);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        System.out.println("Please type \"list\" if unsure to check new order of tasks before performing more actions");
        System.out.println(LINE);
    }

    private static void printList() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String idStr = Integer.toString(i+1);
            System.out.println(idStr + ". " + list.get(i));
        }
        System.out.println(LINE);
    }

    private static void checkValidIndex(int ind) throws ChairdException {
        if (ind <= 0 || ind > list.size()) {
            throw new ChairdException("Task index " + ind + " is invalid!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();
        while (true) {
            String line = sc.nextLine().trim();
            Scanner splitter = new Scanner(line);
            String action = splitter.next();
            try {
                if (action.equals("bye")) {
                    goodbye();
                    break;
                } else if (action.equals("list")) {
                    printList();
                } else if (action.equals("mark")) {
                    if (!splitter.hasNextInt()) {
                        throw new ChairdException("Please provide a valid task number (int) to mark");
                    }
                    int ind = splitter.nextInt();
                    checkValidIndex(ind);
                    mark(ind);
                    storage.save(list);
                } else if (action.equals("unmark")) {
                    if (!splitter.hasNextInt()) {
                        throw new ChairdException("Please provide a valid task number (int) to unmark");
                    }
                    int ind = splitter.nextInt();
                    checkValidIndex(ind);
                    unmark(ind);
                    storage.save(list);
                } else if (action.equals("todo")) {
                    if (!splitter.hasNextLine()) {
                        throw new ChairdException("Please include a task description");
                    }
                    String task = splitter.nextLine().trim();
                    Task newTask = new Todo(task);
                    append(newTask);
                    storage.save(list);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + newTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    System.out.println(LINE);
                } else if (action.equals("deadline")) {
                    if (!splitter.hasNextLine()) {
                        throw new ChairdException("Please include a task description");
                    }
                    String task = splitter.nextLine().trim();
                    String[] parts = task.split("/by", 2);
                    String description = parts[0].trim();
                    String by = parts.length > 1 ? parts[1].trim() : null;
                    if (by == null) {
                        throw new ChairdException("Hey you don't have a valid deadline for the Deadline task! Use (name)/by(time)!");
                    }
                    Task newTask = new Deadline(description, by);
                    append(newTask);
                    storage.save(list);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + newTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    System.out.println(LINE);
                } else if (action.equals("event")) {
                    if (!splitter.hasNextLine()) {
                        throw new ChairdException("Please include a task description");
                    }
                    String task = splitter.nextLine().trim();
                    String[] parts = task.split("/from", 2);
                    String description = parts[0].trim();
                    String by = parts.length > 1 ? parts[1].trim() : null;
                    if (by == null) {
                        throw new ChairdException("Hey you don't have a valid date for the Event task! Use (name)/from(date)!");
                    }
                    Task newTask = new Event(description, by);
                    append(newTask);
                    storage.save(list);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + newTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    System.out.println(LINE);
                } else if (action.equals("delete")) {
                    if (!splitter.hasNextInt()) {
                        throw new ChairdException("Please provide a valid task number (int) to delete");
                    }
                    int ind = splitter.nextInt();
                    checkValidIndex(ind);
                    delete(ind);
                    storage.save(list);
                } else {
                    throw new ChairdException("Hey, you did not enter a valid command/action. Please try again :)");
                }
            } catch (ChairdException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }
        sc.close();
    }
}
