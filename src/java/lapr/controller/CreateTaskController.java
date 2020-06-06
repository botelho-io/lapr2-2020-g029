package lapr.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.list.ListTask;
import lapr.model.App;
import lapr.model.Organization;
import lapr.model.Task;
import autorizacao.model.SessaoUtilizador;
import autorizacao.AuthFacade;
import lapr.ui.console.utils.Utils;

public class CreateTaskController {


        private App plataforma;
        /**
        * Instance of the organization.
        */
        private Organization organization;
        /**
        * User session.
        */
        private SessaoUtilizador sessao;
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
     *
     * @param id of the task.
     * @param description of the task.
     * @param m_iDurationInHours duration it took to complete the task.
     * @param m_dCostPerHourOfJuniorEur cost per hour a junior freelancer receives for this task.
     * @param category he category this task is in.
     */
    public boolean newTask (String id, String description, int m_iDurationInHours, double m_dCostPerHourOfJuniorEur, String category) throws Exception {
        try {
            this.autorizacao = this.plataforma.getAutorizacaoFacade();
            this.sessao = this.autorizacao.getSessaoAtual();
            this.emailUser = this.sessao.getEmailUtilizador();
            this.organization = this.plataforma.getRegistOrganization().getOrganizationByEmailUser(emailUser);
            this.task = ListTask.newTask( id, description, m_iDurationInHours, m_dCostPerHourOfJuniorEur, category);
            return this.organization.getListTask().validatesTask(this.task);
        } catch(RuntimeException ex){
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
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

