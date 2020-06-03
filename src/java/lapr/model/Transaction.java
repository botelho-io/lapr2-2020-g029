/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.controller.AppPOE;
import lapr.utils.Expertise;

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
     * Constructor.
     * @param freelancer The freelancer that completed the task.
     * @param task Tha task completed by the freelancer.
     * @param paymentDetails The payment details of the transaction.
     */
    public Transaction(Freelancer freelancer, Task task, PaymentDetails paymentDetails) {
        this.m_oFreelancer = freelancer;
        this.m_oTask = task;
        this.m_oPaymentDetails = paymentDetails;
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
     * Creates a new task.
     * @param id The id of the task.
     * @param description A short description of the task.
     * @param durationInHours The duration it took to complete the task.
     * @param costPerHourOfJuniorEur The cost per hour a junior freelancer receives for this task.
     * @param category The category this task is in.
     * @return The task created.
     */
    public static Task newTask(String id, String description, int durationInHours, double costPerHourOfJuniorEur, String category) {
        return new Task(id, description, durationInHours, costPerHourOfJuniorEur, category);
    }

    /**
     * Makes a payment on the transaction.
     * @return True if the payment was made, false otherwise.
     */
    public boolean makeBankTransfer() {
        boolean success = AppPOE.getInstance().getApp().getPaymentAPI().payTo(
            getFreelancer().getId(),    // The ID of the freelancer to pay to.
            getFreelancer().getIBAN(),  // The IBAN of the freelancer to pay.
            getTask().getId(),          // The ID of the task this payment is for.
            getTask().getM_strDescription(), // The description of the task this payment is for.
            getAmount(),                // The amount in euros to pay to the freelancer.
            getNativeAmount()           // The amount in the freelancer's native currency to pay.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return m_oFreelancer.equals(that.m_oFreelancer) &&
                m_oTask.equals(that.m_oTask) &&
                m_oPaymentDetails.equals(that.m_oPaymentDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_oFreelancer, m_oTask, m_oPaymentDetails);
    }
}
