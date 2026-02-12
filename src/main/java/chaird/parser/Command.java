package chaird.parser;
/**
 * Command class encapsulates commands that are broken down by
 * the Parser class to be accessed by other classes.
 */
public class Command {
    private final String action, desc;
    private String date = null;
    private final int index;

    public Command(String action, int index, String desc) {
        this.index = index;
        this.action = action;
        this.desc = desc;
    }
    public Command(String action, int ind, String desc, String date) {
        this.index = ind;
        this.action = action;
        this.desc = desc;
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public String getAction() {
        return action;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }
}
