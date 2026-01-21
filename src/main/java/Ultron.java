import java.util.ArrayList;
import java.util.Scanner;

public class Ultron {
    private final static String LINE = "------------------------------------------------";
    private static ArrayList<String> list = new ArrayList<>();

    private static void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Ultron\nWhat can I do for you?");
        System.out.println(LINE);
    }

    private static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void append(String str) {
         list.add(str);
    }

    private static void printList() {
        System.out.println(LINE);
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
            if (line.equals("bye")) {
                goodbye();
                break;
            } else if (line.equals("list")) {
                printList();
            } else {
                append(line);
                System.out.println(LINE);
                System.out.println("added: " + line);
                System.out.println(LINE);
            }
        }
        sc.close();
    }
}
