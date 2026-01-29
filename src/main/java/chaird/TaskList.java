package chaird;

import java.util.ArrayList;
/**
 * Manages a collection of Task objects.
 * Allows for adding, deleting, marking, unmarking and size queries.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a new TaskList with the provided tasks.
     *
     * @param tasks the initial list of tasks (may be empty)
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param t the Task to add
     */
    public void add(Task t) {
        list.add(t);
    }
    /**
     * Marks a task as complete using its index.
     *
     * @param ind the index of the task in the list
     * @throws ChairdException if the index is invalid (≤0 or > list size)
     */
    public void mark(int ind) throws ChairdException {
        checkValidIndex(ind);
        list.get(ind - 1).mark();
    }

    /**
     * Marks a task as incomplete using its index.
     *
     * @param ind the index of the task in the list
     * @throws ChairdException if the index is invalid (≤0 or > list size)
     */
    public void unmark(int ind) throws ChairdException {
        checkValidIndex(ind);
        list.get(ind - 1).unmark();
    }

    /**
     * Deletes a task from the list using its index.
     *
     * @param ind the index of the task in the list
     * @throws ChairdException if the index is invalid (≤0 or > list size)
     */
    public void delete(int ind) throws ChairdException {
        checkValidIndex(ind);
        list.remove(ind - 1);
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    private void checkValidIndex(int ind) throws ChairdException {
        if (ind <= 0 || ind > list.size()) {
            throw new ChairdException("Task index " + ind + " is invalid!");
        }
    }
}
