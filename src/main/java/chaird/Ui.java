package chaird;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final static String LINE = "---------------------------------------------------------------------------------------------";

    public void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Chaird (chat)\nWhat can I do for you?");
        System.out.println(LINE);
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void mark(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(LINE);
    }

    public void unmark(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(LINE);
    }

    public void delete(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + size + " tasks in the list");
        System.out.println("Please type \"list\" if unsure to check new order of tasks before performing more actions");
        System.out.println(LINE);
    }

    public void printList(ArrayList<Task> list) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String str = Integer.toString(i + 1);
            System.out.println(str + ". " + list.get(i));
        }
        System.out.println(LINE);
    }

    public void add(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + size + " tasks in the list");
        System.out.println(LINE);
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine().trim();
    }

    public void printError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
}
