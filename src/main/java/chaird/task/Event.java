package chaird.task;

import chaird.exception.ChairdException;

/**
 * Represents an Event task with a specific date.
 * Extends the Task class and adds date functionalities.
 */
public class Event extends Task{
    protected Date from;

    /**
     * Constructs a new Task class with the description and date.
     * Event is incomplete by default.
     *
     * @param description the task description
     * @param from the date string in appropriate format
     * @throws ChairdException if the due date string is invalid
     */
    public Event(String description, String from) throws ChairdException {
        super(description);
        this.from = new Date(from);
    }
    /**
     * Constructs a new Task class with the description and date.
     * Allows the specification of completeness of the task.
     *
     * @param description the task description
     * @param isDone whether the task is marked as complete or not
     * @param from the date string in appropriate format
     * @throws ChairdException if the due date string is invalid
     */
    public Event(String description, boolean isDone, String from) throws ChairdException{
        super(description, isDone);
        this.from = new Date(from);
    }

    public Date getFrom() {
        return this.from;
    }

    @Override
    public String storageLine() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.from.storageLineStr();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ")";
    }
}
