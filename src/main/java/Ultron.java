import java.util.Scanner;

public class Ultron {
    private final static String LINE = "------------------------------------------------";

    private static void greet() {
        System.out.println("Hello! I'm Ultron\nWhat can I do for you?");
        System.out.println(LINE);
    }

    private static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
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
            }
            System.out.println(line);
            System.out.println(LINE);
        }
        sc.close();
    }
}
