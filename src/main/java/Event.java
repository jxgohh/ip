public class Event extends Task{
    protected String from;

    public Event(String description, String from) {
        super(description);
        this.from = from;
    }

    public Event(String description, boolean completed, String from) {
        super(description, completed);
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }
    @Override
    public String storageLine() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ")";
    }
}
