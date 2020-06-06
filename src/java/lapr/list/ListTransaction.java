package lapr.list;

import lapr.api.EmailAPI;
import lapr.api.MonetaryConversionAPI;
import lapr.controller.AppPOE;
import lapr.model.*;
import lapr.utils.Triplet;

import java.time.LocalDate;
import java.util.*;

/**
 * Represents a list of transactions.
 */
public class ListTransaction implements Iterable<Transaction> {
    /**
     * The list of the transactions held by the list.
     */
    Set<Transaction> m_setTransaction;

    /**
     * Constructor.
     */
    public ListTransaction() {
        m_setTransaction = new HashSet<>();
    }

    /**
     * @return An iterator for the list.
     */
    @Override
    public Iterator<Transaction> iterator() {
        return m_setTransaction.iterator();
    }

    /**
     * Creates a new transaction.
     * @param freelancer The freelancer that completed the task.
     * @param task Tha task completed by the freelancer.
     * @param endDate The date the task ended.
     * @param hoursDelay The delay the freelancer took to execute the task.
     * @param description A textual description of the quality of the work done by the freelancer.
     * @return The new task.
     */
    public static Transaction newTransaction(Freelancer freelancer, Task task, LocalDate endDate, int hoursDelay, String description) {
        return new Transaction(freelancer, task, Transaction.newPaymentDetails(false), Transaction.newTaskExecutionDetails(endDate, hoursDelay, description));
    }

    /**
     * Creates a new transaction.
     * @param freelancer The freelancer that completed the task.
     * @param task Tha task completed by the freelancer.
     * @param paymentDetails The payment details of the transaction.
     * @param details The details about the execution of the task.
     * @return The new task.
     */
    public static Transaction newTransaction(Freelancer freelancer, Task task, PaymentDetails paymentDetails, TaskExecutionDetails details) {
        return new Transaction(freelancer, task, paymentDetails, details);
    }

    /**
     * Removes a transaction from the list.
     * @param trs Transaction to remove.
     * @return True if the transaction is removed, false otherwise.
     */
    public boolean remove(Transaction trs) {
        return m_setTransaction.remove(trs);
    }

    /**
     * Validates a transaction. For a transaction to be valid it has to be the only transaction that refers to
     * a task.
     * @param trs The transaction to validate.
     * @return True if the task is valid, false otherwise.
     */
    public boolean validate(Transaction trs) {
        return !this.m_setTransaction.contains(trs);
    }

    /**
     * Adds a transaction to the list.
     * @param trs Transaction to add.
     * @return True if the transaction is added, false otherwise.
     */
    private boolean add(Transaction trs) {
        if(m_setTransaction.add(trs)) {
            trs.getTask().setExecutor(trs.getFreelancer());
            return true;
        }
        return false;
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
        for(Transaction trs : m_setTransaction) {
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

    public boolean addTransaction(Transaction tr) {
        if(!validate(tr)) return false;
        return add(tr);
    }

    /**
     * Group the transaction by the freelancer that executed them.
     * @return A map that makes a freelancer correspond to a list of their executed transactions in the organization.
     */
    public Map<Freelancer, List<Transaction>> getGroupedTransactions() {
        final Map<Freelancer, List<Transaction>> fre_trs = new HashMap<>();
        for(final Transaction trs : this) {
            final Freelancer fre = trs.getFreelancer();
            final List<Transaction> lt = fre_trs.get(fre);
            if(lt != null) {
                lt.add(trs);
            } else {
                final ArrayList<Transaction> ltt = new ArrayList<Transaction>();
                ltt.add(trs);
                fre_trs.put(fre, ltt);
            }
        }
        return fre_trs;
    }

    /**
     * Group the transaction by the freelancer that executed them.
     * @param year The year of the transactions.
     * @return A map that makes a freelancer correspond to a list of their executed transactions in the organization
     * on the year specified.
     */
    public Map<Freelancer, List<Transaction>> getGroupedTransactionsInYear(final int year) {
        final Map<Freelancer, List<Transaction>> fre_trs = new HashMap<>();
        for(final Transaction trs : this) {
            if(trs.getExecutionDetails().getEndDate().getYear() == year) {
                final Freelancer fre = trs.getFreelancer();
                final List<Transaction> lt = fre_trs.get(fre);
                if (lt != null) {
                    lt.add(trs);
                } else {
                    final ArrayList<Transaction> ltt = new ArrayList<Transaction>();
                    ltt.add(trs);
                    fre_trs.put(fre, ltt);
                }
            }
        }
        return fre_trs;
    }
}
