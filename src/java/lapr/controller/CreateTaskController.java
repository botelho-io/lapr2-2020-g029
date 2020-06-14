package lapr.controller;

import lapr.lists.ListTask;
import lapr.model.App;
import lapr.model.Organization;
import lapr.model.Task;
import authorization.model.UserSession;
import authorization.AuthFacade;

/**
 * Class that is responsible for creating a new task and adding to tha task list of the organization.
 * @author André Botelho and Ricardo Moreira.
 */
public class CreateTaskController {

        /**
        * Instance of the app.
        */
        private App app;
        /**
        * Instance of Organization of the current user.
        */
        private Organization organization;
        /**
        * User session.
        */
        private UserSession session;
        /**
        * ID of the collaborator.
        */
        private String emailUser;
        /**
         *  Instance of Task of the transaction.
         */
        private Task task;

      /**
      * An Authorization Facade instance.
      */
     private AuthFacade authorization;

    /**
     * Constructor.
     */
     public CreateTaskController() {
        this.app = AppPOE.getInstance().getApp();
    }

    /**
     * Build an instance of organization receiving the name, manager and collaborator.
     * @param id of the task.
     * @param description of the task.
     * @param durationInHours duration it took to complete the task.
     * @param m_dCostPerHourOfJuniorEur cost per hour a junior freelancer receives for this task.
     * @param category he category this task is in.
     * @return The new task.
     */
    public boolean newTask (String id, String description, double durationInHours, double m_dCostPerHourOfJuniorEur, String category) {
        try {
            this.authorization = this.app.getAuthFacade();
            this.session = this.authorization.getCurrentSession();
            this.emailUser = this.session.getEmailUser();
            this.organization = this.app.getRegistOrganization().getOrganizationByEmailUser(emailUser);
            this.task = ListTask.newTask( id, description, durationInHours, m_dCostPerHourOfJuniorEur, category);
            return this.organization.getListTask().validatesTask(this.task);
        } catch(RuntimeException ex){
            System.out.println(ex.getMessage());
            this.task = null;
            return false;
        }
    }

    /**
     * Register task of the organization.
     * @return register on the organization.
     */
    public boolean registTask() {
        return this.organization.getListTask().registerTask(this.task);
    }

    /**
     * Get task by string.
     * @return task.
     */
    public String getTaskString() {
            return this.task.toString();
        }
}

