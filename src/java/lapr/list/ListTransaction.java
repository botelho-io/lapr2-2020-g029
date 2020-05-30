package lapr.list;

import lapr.model.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of transactions.
 */
public class ListTransaction implements Iterable<Transaction> {
    /**
     * The list of the transactions held by the list.
     */
    List<Transaction> m_lstTransaction;

    /**
     * @return An iterator for the list.
     */
    @Override
    public Iterator<Transaction> iterator() {
        return m_lstTransaction.iterator();
    }

    /**
     * Removes a transaction from the list.
     * @param trs Transaction to remove.
     * @return True if the transaction is removed, false otherwise.
     */
    public boolean remove(Transaction trs) {
        return m_lstTransaction.remove(trs);
    }

    /**
     * Adds a transaction to the list.
     * @param trs Transaction to add.
     * @return True if the transaction is added, false otherwise.
     */
    public boolean add(Transaction trs) {
        return m_lstTransaction.add(trs);
    }

    /**
     * Send an e-mail to all freelancers who have transaction on this list
     * notifying that a payment has been made on them.
     * <p>The email will contain a list of the amount to receive for each task
     * and the total amount of money in all the transactions on this list, the
     * amount will be both in euros and the currency of the freelancer's
     * country.</p>
     * @return True if all emails were successfully sent, false otherwise.
     */
    public boolean emailAboutPayment() {
        // TODO: implement
        return false;
    }
}
