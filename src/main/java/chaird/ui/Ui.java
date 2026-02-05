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
     *
     * @return greeting message
     */
    public String greet() {
        return "Hello! I'm Chaird (chat)\nWhat can I do for you?";
    }
    /**
     * Prints the goodbye message when user exits the application.
     *
     * @return goodbye message
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints confirmation messages when a task is marked as completed.
     *
     * @param task the task that was marked done
     * @return confirmation message
     */
    public String mark(Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task;
    }

    /**
     * Prints confirmation messages when a task is unmarked as completed.
     *
     * @param task the task that was marked undone
     * @return confirmation message
     */
    public String unmark(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n" + task;
    }

    /**
     * Prints confirmation messages when a task is deleted.
     *
     * @param task the task that was deleted
     * @param size the size of the list of tasks after deletion
     * @return confirmation message for deleting task
     */
    public String delete(Task task, int size) {
        return "Noted. I've removed this task:" + "\n   " + task + "\n"
                + "Now you have " + size + " tasks in the list \n"
                + "Please type \"list\" if unsure to check new "
                + "order of tasks before performing more actions";
    }
    /**
     * Prints all tasks in the tasklist.
     *
     * @param tasks the ArrayList of all tasks in the application
     * @return all list contents
     */
    public String printList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i+1);
            sb.append(". ");
            sb.append(tasks.get(i));
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Prints confirmation messages when a new task is added.
     *
     * @param task the task that was added
     * @param size the size of the list after the task is added
     * @return confirmation message of adding task
     */
    public String add(Task task, int size) {
        return "Got it. I've added this task: \n"
                + "    " + task + "\n"
                + "Now you have " + size + " tasks in the list";
    }

    /**
     * Prints all tasks in the search tasklist.
     *
     * @param tasks the ArrayList of all matching tasks
     * @return string of all matching tasks in list
     */
    public String find(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i+1);
            sb.append(". ");
            sb.append(tasks.get(i));
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Prints an error message to inform the user of an invalid operation or input.
     *
     * @param message the error message to display
     * @return error message
     */
    public String printError(String message) {
        return message;
    }
}
