public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean completed) {
        this.description = description;
        this.isDone = completed;
    }
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String storageLine() {
        return this.description;
    }

    @Override
    public String toString() {
        String box = "[" + getStatusIcon() + "]";
        return box + " " + this.description;
    }
}