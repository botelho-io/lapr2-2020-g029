package lapr.controller;

import lapr.model.*;
import lapr.ui.console.utils.Utils;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.model.Organization;
import autorizacao.model.SessaoUtilizador;
import autorizacao.AutorizacaoFacade;
import java.time.LocalTime;


public class DefineTimeController {

    public static DefineTimeController getInstace;
    private App plataforma;
    /** Instance of the paymentScheduler.
     *
     * */
    private PaymentScheduler paymentScheduler;
    /**
     * Instance of the organization.
     */
    private Organization organization;
    /**
     * User session.
     */
    private SessaoUtilizador sessao;
    /**
     * An AutorizacaoFacade instance.
     */
    private AutorizacaoFacade autorizacao;
    /**
     * id of the manager.
     */
    private String emailUser;


    public DefineTimeController() {
        this.plataforma = AppPOE.getInstance().getApp();
    }

    /**
     * Builds an instance of payment scheduler.
     * @param dayMonth The day of the month the payment are to be made.
     * @param timeOfDay The time of day to make the payments.
     */
    public boolean newPaymentScheduler(int dayMonth, LocalTime timeOfDay)  throws Exception {
        try {
            this.autorizacao = this.plataforma.getAutorizacaoFacade();
            this.sessao = this.autorizacao.getSessaoAtual();
            this.emailUser = this.sessao.getEmailUtilizador();
            this.organization = this.plataforma.getRegistOrganization().getOrganizationByEmailUser(emailUser);
            this.paymentScheduler = this.organization.newPaymentScheduler(dayMonth, timeOfDay);
            return this.organization.validatesPaymentScheduler(this.paymentScheduler);
        } catch (RuntimeException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.paymentScheduler = null;
            return false;
        }
    }
}