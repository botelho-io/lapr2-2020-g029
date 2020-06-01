package lapr.controller;

import lapr.list.ListTransaction;
import lapr.model.Organization;
import lapr.model.PaymentScheduler;
import lapr.regist.RegistPayment;

import java.util.TimerTask;

public class MakePaymentTask extends TimerTask {
    /**
     * The organization to make the payments on.
     */
    Organization m_oOrganization;
    /**
     * The payment scheduler responsible for this task.
     */
    PaymentScheduler m_oPaymentScheduler;
    /**
     * Constructor.
     * @param organization The organization to make the payments on.
     * @param paymentScheduler The payment scheduler responsible for this task.
     */
    public MakePaymentTask(Organization organization, PaymentScheduler paymentScheduler) {
        this.m_oOrganization = organization;
        this.m_oPaymentScheduler = paymentScheduler;
    }
    /**
     * Called by tis tasks timer. Starts to make payments.
     */
    @Override
    public void run() {
        makePayments();
        m_oPaymentScheduler.scheduleNextMonth();
    }
    /**
     * Makes payments on the unpaid transaction of the organization.
     */
    private void makePayments() {
        ListTransaction lt = m_oOrganization.getListTransaction();
        RegistPayment rp = AppPOE.getInstance().getApp().getRegistPayment();
        rp.makePayments(lt, m_oOrganization);
    }
}
