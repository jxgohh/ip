import java.util.Scanner;
public class Parser {
    public static Command parseLine(String line) throws ChairdException {
        Scanner splitter = new Scanner(line);
        String action = splitter.next();
        if (action.equals("bye") || action.equals("list")) {
            return new Command(action, -1, null);
        }
        if (action.equals("mark") || action.equals("unmark") || action.equals("delete")) {
            if (!splitter.hasNextInt()) {
                throw new ChairdException("Please provide a valid task number (int) to delete");
            }
            int ind = splitter.nextInt();
            return new Command(action, ind, null);
        }
        if (action.equals("todo")) {
            if (!splitter.hasNextLine()) {
                throw new ChairdException("Please include a task description");
            }
            return new Command(action, -1, splitter.nextLine().trim());
        }
        if (action.equals("deadline") || action.equals("event")) {
            if (!splitter.hasNextLine()) {
                throw new ChairdException("Please include a task description");
            }
            String task = splitter.nextLine().trim();
            String[] parts = task.split("/by", 2);
            String description = parts[0].trim();
            String time = parts.length > 1 ? parts[1].trim() : null;
            if (time == null) {
                throw new ChairdException("Hey you don't have a valid deadline for the Deadline task! Use (name)/by(time)!");
            }
            return new Command(action, -1, description, time);
        }
        throw new ChairdException("Hey, you did not enter a valid command/action. Please try again :)");
    }
}
