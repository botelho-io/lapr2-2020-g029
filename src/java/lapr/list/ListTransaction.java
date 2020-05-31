package lapr.list;

import javafx.util.Pair;
import lapr.api.EmailAPI;
import lapr.api.MonetaryConversionAPI;
import lapr.controller.AppPOE;
import lapr.model.Transaction;
import lapr.utils.Triplet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        // Maps [Freelancer email] to [Total amount paid, E-mail message, freelancer's country of origin]
        Map<String, Triplet<Double, String, String>> map = new HashMap<>();
        MonetaryConversionAPI mcapi = AppPOE.getInstance().getApp().getMonetaryConversionAPI();
        EmailAPI eapi = AppPOE.getInstance().getApp().getEmailAPI();
        // Put all payment in message
        for(Transaction trs : m_lstTransaction) {
            final String email = trs.getFreelancer().getEmail();
            Triplet<Double, String, String> val = map.get(email);
            if(val == null) val = new Triplet<>(0.0, "", trs.getFreelancer().getCountry());
            final Double currAmount = trs.getAmount();
            val.setFirst(val.getFirst() + currAmount);
            val.setSecond(
                    val.getSecond() + String.format("TASK [%s] (ID: %s) - EUR [%f] - NATIVE CURRENCY [%f]\n",
                        trs.getTask().getDescription(),
                        trs.getTask().getId(),
                        currAmount,
                        mcapi.convert(val.getThird(), currAmount)));
            map.put(email, val);
        }
        // Send message with total at the end
        boolean allSent = true;
        for(String email : map.keySet()) {
            final Triplet<Double, String, String> val = map.get(email);
            allSent = allSent & eapi.sendEmail(
                    email,
                    String.format("%s\n\nTOTAL - %f - NATIVE CURRENCY [%f]\n",
                        val.getSecond(),
                        val.getFirst(),
                        mcapi.convert(val.getThird(), val.getFirst())));
        }
        return allSent;
    }
}
