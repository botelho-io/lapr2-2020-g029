package lapr.regist;

import lapr.list.ListTransaction;
import lapr.model.Organization;
import lapr.model.Payment;
import lapr.model.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * Represents a regist of payments.
 */
public class RegistPayment {
    /**
     * The payments contained inside this regist.
     */
    private List<Payment> m_lstPayment;
    /**
     * Makes payments for an organization.
     * @param lt A list of transactions that need to be payed.
     * @param organization The organization that has to pay the transactions.
     */
    public void makePayments(ListTransaction lt, Organization organization) {
        ListTransaction completeTransactions = new ListTransaction();
        Iterator<Transaction> itT = lt.iterator();
        for(Transaction trs : lt) {
            Payment payment = trs.makeBankTransfer(organization);
            add(payment);
            completeTransactions.add(trs);
        }
        for(Transaction trs: completeTransactions)
            lt.remove(trs);
        completeTransactions.emailAboutPayment();
    }
    /**
     * Adds a payment to the regist.
     * @param payment The payment to add.
     * @return True if the payment was added, false otherwise.
     */
    private boolean add(Payment payment) {
        return m_lstPayment.add(payment);
    }
}
