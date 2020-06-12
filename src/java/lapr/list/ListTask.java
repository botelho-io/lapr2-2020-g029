package lapr.list;

import lapr.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListTask implements Serializable {

    /**
     * Setts task list.
     */
    private List<Task> m_lstTask;

    /**
     * Setts task list.
     */
    public ListTask(){
        m_lstTask = new ArrayList<>();
    }

    /**
     * Returns the task list.
     *
     * @return the task list.
     */
    public List<Task> getTasks() {
        return m_lstTask;
    }

    /**
     * Creates a new task.
     * @param id The id of the task.
     * @param description A short description of the task.
     * @param durationInHours The duration it took to complete the task.
     * @param costPerHourOfJuniorEur The cost per hour a junior freelancer receives for this task.
     * @param category The category this task is in.
     * @return The task created.
     */
    public static Task newTask(String id, String description, double durationInHours, double costPerHourOfJuniorEur, String category) {
        return new Task(id, description, durationInHours, costPerHourOfJuniorEur, category, null);
    }

    /**
     * Tests weather a task can be added into the regist.
     * @param task of the organization.
     * @return true if valid.
     */
    public boolean validatesTask(Task task ) {
        return !m_lstTask.contains(task);
    }

    /**
     * Register a new task.
     * @param task Task to regist.
     * @return True if the task was added, false otherwise.
     */
    public boolean registTask(Task task){
        if (validatesTask(task)){
            return add(task);
        } else {
            return false;
        }
    }

    /**
     * Adds task.
     * @param task to add.
     * @return True if the task was added, false otherwise.
     */
    private boolean add(Task task) {
        return m_lstTask.add(task);
    }

    /**
     * Find tasks that have not been executed.
     * @return All the tasks in the list that have not been executed.
     */
    public List<Task> getUnexecutedTasks() {
        ArrayList<Task> ue = new ArrayList<>();
        for(Task tsk : m_lstTask) {
            if(tsk.getExecutor() == null)
                ue.add(tsk);
        }
        return ue;
    }

    /**
     * Searches for a task.
     * @param task Get a freelancer that equal task.
     * @return The Task that will equal task.
     */
    public Task getEqualTask(Task task) {
        for (Task t : this.m_lstTask) {
            if(t.equals(task))
                return task;
        }
        return null;
    }
}
