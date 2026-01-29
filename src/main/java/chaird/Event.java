package chaird;

public class Event extends Task{
    protected Date from;

    public Event(String description, String from) throws ChairdException{
        super(description);
        this.from = new Date(from);
    }

    public Event(String description, boolean completed, String from) throws ChairdException{
        super(description, completed);
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
