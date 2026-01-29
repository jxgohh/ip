package chaird;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
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