package chaird.tasklist;

import chaird.exception.ChairdException;
import chaird.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Manages a collection of Task objects.
 * Allows for adding, deleting, marking, unmarking and size queries.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the provided tasks.
     *
     * @param tasks the initial list of tasks (may be empty)
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Finds all tasks in the tasklist that contains the keyword
     *
     * @param keyword the keyword to search the list for matching tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t: this.tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param t the Task to add
     */
    public void add(Task t) {
        tasks.add(t);
    }
    /**
     * Marks a task as complete using its index.
     *
     * @param ind the index of the task in the list
     * @throws ChairdException if the index is invalid (≤0 or > list size)
     */
    public void mark(int ind) throws ChairdException {
        checkValidIndex(ind);
        tasks.get(ind - 1).mark();
    }

    /**
     * Marks a task as incomplete using its index.
     *
     * @param ind the index of the task in the list
     * @throws ChairdException if the index is invalid (≤0 or > list size)
     */
    public void unmark(int ind) throws ChairdException {
        checkValidIndex(ind);
        tasks.get(ind - 1).unmark();
    }

    /**
     * Deletes a task from the list using its index.
     *
     * @param ind the index of the task in the list
     * @throws ChairdException if the index is invalid (≤0 or > list size)
     */
    public void delete(int ind) throws ChairdException {
        checkValidIndex(ind);
        tasks.remove(ind - 1);
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    private void checkValidIndex(int ind) throws ChairdException {
        if (ind <= 0 || ind > tasks.size()) {
            throw new ChairdException("Task index " + ind + " is invalid!");
        }
    }
}
