package chaird.task;
/**
 * Represents a basic task without a specific date or time.
 * Abstract base class providing base functionalities for all it's children.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with its description.
     * Task is incomplete by default.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Constructs a new Task with its description.
     * Allows the specification of completeness of the task.
     *
     * @param description the task description
     * @param isDone whether the task is marked as complete or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Returns an X if the task is considered as completed.
     *
     * @return a string "X" is boolean isDone is true and empty string if false
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks this task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as uncompleted.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the storage format string for this task.
     * Subclasses override this to include type-specific information.
     *
     * @return the base storage string (just the description)
     */
    public String storageLine() {
        return this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String box = "[" + getStatusIcon() + "]";
        return box + " " + this.description;
    }
}