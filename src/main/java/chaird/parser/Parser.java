package chaird.parser;

import chaird.exception.ChairdException;

import java.util.Scanner;
/**
 * Parses user input strings into structured Command objects.
 * Handles validation of command syntax and extracts parameters for all supported actions.
 */
public class Parser {
    /**
     * Parses a raw user input line into a Command object.
     * Validates syntax and extracts command type, index, task description and time.
     *
     * @param line the raw input string to parse
     * @return a parsed Command object
     * @throws ChairdException if the input is incorrect or contains invalid syntax
     */
    public static Command parseLine(String line) throws ChairdException {
        Scanner splitter = new Scanner(line);
        String action = splitter.next();

        switch (action) {
            case "bye":
            case "list":
                return new Command(action, -1, null);

            case "mark":
            case "unmark":
            case "delete":
                if (!splitter.hasNextInt()) {
                    throw new ChairdException("Please provide a valid task number (int)");
                }
                int ind = splitter.nextInt();
                return new Command(action, ind, null);

            case "todo":
                if (!splitter.hasNextLine()) {
                    throw new ChairdException("Please include a task description");
                }
                return new Command(action, -1, splitter.nextLine().trim());

            case "note":
                if (!splitter.hasNextLine()) {
                    throw new ChairdException("Please include a note description");
                }
                return new Command(action, -1, splitter.nextLine().trim());

            case "find":
                if (!splitter.hasNextLine()) {
                    throw new ChairdException("Please include a keyword");
                }
                String keyword = splitter.nextLine().trim();
                return new Command(action, -1, keyword);

            case "deadline": {
                if (!splitter.hasNextLine()) {
                    throw new ChairdException("Please include a task description");
                }
                String task = splitter.nextLine().trim();
                String[] parts = task.split("/by", 2);
                String description = parts[0].trim();
                String time = parts.length > 1 ? parts[1].trim() : "";
                if (time.isEmpty()) {
                    throw new ChairdException("Hey you don't have a valid deadline for the task! Use (name)/by(time)!");
                }
                return new Command(action, -1, description, time);
            }

            case "event": {
                if (!splitter.hasNextLine()) {
                    throw new ChairdException("Please include a task description");
                }
                String task = splitter.nextLine().trim();
                String[] parts = task.split("/from", 2);
                String description = parts[0].trim();
                String time = parts.length > 1 ? parts[1].trim() : "";
                if (time.isEmpty()) {
                    throw new ChairdException("Hey you don't have a valid time for the event! Use (name)/from(time)!");
                }
                return new Command(action, -1, description, time);
            }

            default:
                throw new ChairdException("Hey, you did not enter a valid command/action. Please try again :)");
        }
    }
}
