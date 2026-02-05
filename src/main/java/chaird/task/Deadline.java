package chaird.task;

import chaird.exception.ChairdException;

/**
 * Represents a task with a specific deadline.
 * Extends the Task class and adds deadline functionalities.
 */
public class Deadline extends Task{
    protected Date by;
    /**
     * Constructs a new Dateline class with the description and due date.
     * Deadline is incomplete by default.
     *
     * @param description the task description
     * @param by the due date string in appropriate format
     * @throws ChairdException if the due date string is invalid
     */
    public Deadline(String description, String by) throws ChairdException {
        super(description);
        this.by = new Date(by);
    }

    /**
     * Constructs a new Dateline class with the description and due date.
     * Allows the specification of completeness of the task.
     *
     * @param description the task description
     * @param isDone whether the task is marked as complete or not
     * @param by the due date string in appropriate format
     * @throws ChairdException if the due date string is invalid
     */
    public Deadline(String description, boolean isDone, String by) throws ChairdException {
        super(description, isDone);
        this.by = new Date(by);
    }

    public Date getBy() {
        return this.by;
    }

    @Override
    public String storageLine() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by.storageLineStr();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
