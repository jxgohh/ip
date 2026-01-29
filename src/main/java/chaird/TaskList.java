package chaird;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    public void add(Task t) {
        list.add(t);
    }
    public void mark(int ind) throws ChairdException {
        checkValidIndex(ind);
        list.get(ind - 1).mark();
    }

    public void unmark(int ind) throws ChairdException {
        checkValidIndex(ind);
        list.get(ind - 1).unmark();
    }

    public void delete(int ind) throws ChairdException {
        checkValidIndex(ind);
        list.remove(ind - 1);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public int size() {
        return list.size();
    }

    private void checkValidIndex(int ind) throws ChairdException {
        if (ind <= 0 || ind > list.size()) {
            throw new ChairdException("Task index " + ind + " is invalid!");
        }
    }
}
