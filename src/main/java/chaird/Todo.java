package chaird;

/**
 * Represents a basic to-do task without a specific date or time
 * Extends the base Task class
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with its description
     * Todo is incomplete by default
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Constructs a new Todo task with its description
     * Allows the specification of completeness of the task
     *
     * @param description the task description
     * @param completed whether the task is marked as complete or not
     */
    public Todo(String description, boolean completed) {
        super(description, completed);
    }

    @Override
    public String storageLine() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}