import java.util.ArrayList;
import java.util.Scanner;

public class Ultron {
    private final static String LINE = "------------------------------------------------";
    private static ArrayList<Task> list = new ArrayList<>();

    private static void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Ultron\nWhat can I do for you?");
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

    private static void printList() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String idStr = Integer.toString(i+1);
            System.out.println(idStr + ". " + list.get(i));
        }
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();
        while (true) {
            String line = sc.nextLine();
            Scanner splitter = new Scanner(line);
            String action = splitter.next();
            if (action.equals("bye")) {
                goodbye();
                break;
            } else if (action.equals("list")) {
                printList();
            } else if (action.equals("mark")) {
                int ind = splitter.nextInt();
                mark(ind);
            } else if (action.equals("unmark")) {
                int ind = splitter.nextInt();
                unmark(ind);
            } else if (action.equals("todo")) {
                System.out.println(LINE);
                String task = splitter.nextLine().trim();
                Task newTask = new Todo(task);
                append(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println("    " + newTask);
                System.out.println("Now you have " + list.size() + " tasks in the list");
                System.out.println(LINE);
            } else if (action.equals("deadline")) {
                System.out.println(LINE);
                String task = splitter.nextLine().trim();
                String[] parts = task.split("/by", 2);
                String description = parts[0].trim();
                String by = parts.length > 1 ? parts[1].trim() : null;
                if (by == null) {
                    System.out.println("Hey you don't have a valid deadline for the Deadline task! Use (name)/by(time)!");
                } else {
                    Task newTask = new Deadline(description, by);
                    append(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + newTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list");

                }
                System.out.println(LINE);
            } else if (action.equals("event")) {
                System.out.println(LINE);
                String task = splitter.nextLine().trim();
                String[] parts = task.split("/from", 2);
                String description = parts[0].trim();
                String by = parts.length > 1 ? parts[1].trim() : null;
                if (by == null) {
                    System.out.println("Hey you don't have a valid date for the Event task! Use (name)/from(date)!");
                } else {
                    Task newTask = new Event(description, by);
                    append(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + newTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                }
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("Hey, you did not enter a valid command/action. Please try again :)");
                System.out.println(LINE);
            }
        }
        sc.close();
    }
}
