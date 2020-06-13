package lapr.controller;

import lapr.lists.ListTask;
import lapr.model.App;
import lapr.model.Organization;
import lapr.model.Task;
import authorization.model.UserSession;
import authorization.AuthFacade;

public class CreateTaskController {
        private App plataforma;
        /**
        * Instance of the organization.
        */
        private Organization organization;
        /**
        * User session.
        */
        private UserSession sessao;
        /**
        * id of the collaborator.
        */
        private String emailUser;
        /*** Instance of the task.
         *
         * */
        private Task task;

     /**
     * An AutorizacaoFacade instance.
      */
     private AuthFacade autorizacao;

     public CreateTaskController() {
        this.plataforma = AppPOE.getInstance().getApp();
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
            this.autorizacao = this.plataforma.getAuthFacade();
            this.sessao = this.autorizacao.getCurrentSession();
            this.emailUser = this.sessao.getEmailUser();
            this.organization = this.plataforma.getRegistOrganization().getOrganizationByEmailUser(emailUser);
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
     * @return registed on the organization.
     */
    public boolean registTask() {
        return this.organization.getListTask().registTask(this.task);
    }

    /**
     * Get task by string.
     * @return task.
     */
    public String getTaskString() {
            return this.task.toString();
        }
}

