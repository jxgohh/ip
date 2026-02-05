package chaird.ui;

import chaird.task.Task;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Handles all user interface interactions for the application.
 * Manages console output formatting, command reading, and user feedback messages.
 */
public class Ui {
    private final static String LINE = "---------------------------------------------------------------------------------------------";
    /**
     * Prints the welcome greeting message when the application starts.
     */
    public void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Chaird (chat)\nWhat can I do for you?");
        System.out.println(LINE);
    }
    /**
     * Prints the goodbye message when user exits the application.
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints confirmation messages when a task is marked as completed.
     *
     * @param task the task that was marked done
     */
    public void mark(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(LINE);
    }

    /**
     * Prints confirmation messages when a task is unmarked as completed.
     *
     * @param task the task that was marked undone
     */
    public void unmark(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(LINE);
    }

    /**
     * Prints confirmation messages when a task is deleted.
     *
     * @param task the task that was deleted
     * @param size the size of the list of tasks after deletion
     */
    public void delete(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + size + " tasks in the list");
        System.out.println("Please type \"list\" if unsure to check new order of tasks before performing more actions");
        System.out.println(LINE);
    }
    /**
     * Prints all tasks in the tasklist.
     *
     * @param tasks the ArrayList of all tasks in the application
     */
    public void printList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String str = Integer.toString(i + 1);
            System.out.println(str + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Prints confirmation messages when a new task is added.
     *
     * @param task the task that was added
     * @param size the size of the list after the task is added
     */
    public void add(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + size + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * Prints all tasks in the search tasklist.
     *
     * @param tasks the ArrayList of all matching tasks
     */
    public void find(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String str = Integer.toString(i + 1);
            System.out.println(str + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }
    /**
     * Reads the next line of input that the user puts into the scanner.
     *
     * @param sc the scanner to read from
     * @return the trimmed command string entered by the user
     */
    public String readCommand(Scanner sc) {
        return sc.nextLine().trim();
    }

    /**
     * Prints an error message to inform the user of an invalid operation or input.
     *
     * @param message the error message to display
     */
    public void printError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
}
