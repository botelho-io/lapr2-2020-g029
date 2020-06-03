package lapr.list;

import lapr.model.Task;
import java.util.HashSet;
import java.util.Set;

public class TaskList {

    /**
     * Setts task list.
     */
    private Set<Task> m_lstTarefas;

    /**
     * Setts task list.
     */
    public TaskList(){
        m_lstTarefas = new HashSet<Task>();
    }

    /**
     * Returns the task list.
     *
     * @return the task list.
     */
    public Set<Task> getTasks() {
        return m_lstTarefas;
    }
}
