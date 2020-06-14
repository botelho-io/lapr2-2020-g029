package lapr.controller;

import authorization.AuthFacade;
import authorization.model.UserSession;
import lapr.model.*;
import lapr.model.Organization;
import java.time.LocalTime;

/**
 * Class that is responsible for defining the payment scheduler of the organization.
 * @author André Botelho and Ricardo Moreira.
 */
public class DefinePaymentSchedulerController {
    /** Instance of the app.
     *
     * */
    private App app;
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
    private UserSession session;
    /**
     * An Authorization Facade instance.
     */
    private AuthFacade auth;
    /**
     * id of the manager.
     */
    private String emailUser;

    /**
     * Constructor.
     */
    public DefinePaymentSchedulerController() {
        this.app = AppPOE.getInstance().getApp();
    }

    /**
     * Builds an instance of payment scheduler.
     * @param dayMonth The day of the month the payment are to be made.
     * @param timeOfDay The time of day to make the payments.
     */
    public void newPaymentScheduler(int dayMonth, LocalTime timeOfDay) {
        this.auth = this.app.getAuthFacade();
        this.session = this.auth.getCurrentSession();
        this.emailUser = this.session.getEmailUser();
        this.organization = this.app.getRegistOrganization().getOrganizationByEmailUser(emailUser);
        if(!organization.validatesPaymentScheduler(dayMonth, timeOfDay))
            throw new IllegalArgumentException("Specified time is invalid, day of month should be in the range [1, 28]");
        if(!this.organization.setPaymentScheduler(dayMonth, timeOfDay))
            throw new IllegalArgumentException("Could not set specified schedule");
    }
}