package chaird.task;

public class Note extends Task {
    public Note(String description) {
        super(description, false);
    }
    @Override
    public String storageLine() {
        return "N | " + "0" + " | " + description;
    }
    @Override
    public String toString() {
        return "[N]" + super.toString();
    }
}
