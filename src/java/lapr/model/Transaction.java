/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.controller.AppPOE;
import lapr.utils.Expertise;

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
     * Makes a payment on the transaction.
     * @return True if the payment was made, false otherwise.
     */
    public boolean makeBankTransfer() {
        boolean success = AppPOE.getInstance().getApp().getPaymentAPI().payTo(
            getFreelancer().getId(),    // The ID of the freelancer to pay to.
            getFreelancer().getIBAN(),  // The IBAN of the freelancer to pay.
            getTask().getId(),          // The ID of the task this payment is for.
            getTask().getDescription(), // The description of the task this payment is for.
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
}
