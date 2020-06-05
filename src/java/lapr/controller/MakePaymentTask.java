package lapr.controller;

import lapr.list.ListTransaction;
import lapr.model.Organization;
import lapr.model.PaymentScheduler;
import lapr.model.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
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

        ListTransaction completeTransactions = new ListTransaction();
        for(Transaction trs : lt) {
            if(!trs.getPaymentDetails().isPayed())
                if(trs.makeBankTransfer())
                    completeTransactions.addTransaction(trs);
        }
        completeTransactions.emailAboutPayment();
    }
}
