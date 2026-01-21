public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean toDo= false;
    protected boolean deadline = false;
    protected boolean event = false;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String box = "[" + getStatusIcon() + "]";
        return box + " " + description;
    }
}