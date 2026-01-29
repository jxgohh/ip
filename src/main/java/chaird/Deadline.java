public class Deadline extends Task{
    protected Date by;

    public Deadline(String description, String by) throws ChairdException {
        super(description);
        this.by = new Date(by);
    }

    public Deadline(String description, boolean completed, String by) throws ChairdException {
        super(description, completed);
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
