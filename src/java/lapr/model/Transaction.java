/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.controller.AppPOE;
import lapr.utils.Expertise;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a monetary transaction made to a freelancer because of a task.
 */
public class Transaction {
    /**
     * The freelancer this transaction refers to.
     */
    private Freelancer m_oFreelancer;
    /**
     * The task this transaction is paying.
     */
    private Task m_oTask;
    /**
     * The details about the payment made on this transaction.
     */
    private PaymentDetails m_oPaymentDetails;
    /**
     * The details about the execution of the task.
     */
    private TaskExecutionDetails m_oDetails;

    /**
     * Constructor.
     * @param freelancer The freelancer that completed the task.
     * @param task Tha task completed by the freelancer.
     * @param paymentDetails The payment details of the transaction.
     * @param details The details about the execution of the task.
     */
    public Transaction(Freelancer freelancer, Task task, PaymentDetails paymentDetails, TaskExecutionDetails details) {
        if(freelancer == null) throw new IllegalArgumentException("Transaction - Freelancer cannot be null");
        if(task == null) throw new IllegalArgumentException("Transaction - Task cannot be null");
        if(paymentDetails == null) throw new IllegalArgumentException("Transaction - Payment Details cannot be null");
        if(details == null) throw new IllegalArgumentException("Transaction - Task ExecutionDetails cannot be null");
        this.m_oFreelancer = freelancer;
        this.m_oTask = task;
        this.m_oPaymentDetails = paymentDetails;
        this.m_oDetails = details;
    }

    /**
     * Constructor.
     * @param endDate The date the task ended.
     * @param hoursDelay The delay the freelancer took to execute the task.
     * @param description A textual description of the quality of the work done by the freelancer.
     */
    public static TaskExecutionDetails newTaskExecutionDetails(LocalDate endDate, int hoursDelay, String description) {
        return new TaskExecutionDetails(endDate, hoursDelay, description);
    }

    /**
     * Creates new payment details.
     * @param isPayed True if the payment was made, false otherwise.
     * @return The payment details.
     */
    public static PaymentDetails newPaymentDetails(boolean isPayed) {
        return new PaymentDetails(isPayed);
    }

    /**
     * Makes a payment on the transaction.
     * @return True if the payment was made, false otherwise.
     */
    public boolean makeBankTransfer() {
        boolean success = AppPOE.getInstance().getApp().getPaymentAPI().payTo(
            getFreelancer().getId(),            // The ID of the freelancer to pay to.
            getFreelancer().getIBAN(),          // The IBAN of the freelancer to pay.
            getTask().getId(),                  // The ID of the task this payment is for.
            getTask().getDescription(),    // The description of the task this payment is for.
            getAmount(),                        // The amount in euros to pay to the freelancer.
            getNativeAmount()                   // The amount in the freelancer's native currency to pay.
        );
        getPaymentDetails().setPayed(success);
        return success;
    }
    /**
     * @return The IBAN of the freelancer this transaction refers to.
     */
    private String getFreelancerIBAN() {
        return m_oFreelancer.getIBAN();
    }
    /**
     * @return Amount (in euros) to pay to the freelancer for the task.
     */
    public Double getAmount() {
        double total = m_oTask.getCostPerHourOfJuniorEur() * m_oTask.getDurationInHours();
        if(m_oFreelancer.getLevelOfExpertise() == Expertise.SENIOR) {
            total *= 2;
        }
        return total;
    }
    /**
     * @return Amount (in the freelancer's native currency) to pay to the freelancer for the task.
     */
    public Double getNativeAmount() {
        return AppPOE.getInstance().getApp().getMonetaryConversionAPI().convert(getFreelancer().getCountry(), getAmount());
    }
    /**
     * @return The freelancer this transaction refers to.
     */
    public Freelancer getFreelancer() {
        return m_oFreelancer;
    }
    /**
     * @return The task this transaction is paying.
     */
    public Task getTask() {
        return m_oTask;
    }
    /**
     * @return The details about the payment made on this transaction.
     */
    public PaymentDetails getPaymentDetails() {
        return m_oPaymentDetails;
    }

    /**
     * @return The details of the execution of the task.
     */
    public TaskExecutionDetails getExecutionDetails() {
        return m_oDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return m_oTask.equals(that.m_oTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_oTask);
    }
}
