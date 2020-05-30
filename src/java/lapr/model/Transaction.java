/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.controller.AppPOE;

/**
 * Represents a monetary transaction made to a freelancer because of a task.
 */
public class Transaction {
    /**
     * The freelancer this transaction refers to.
     */
    private Freelancer m_oFreelancer;
    /**
     * Makes a payment on the transaction.
     * @param organization The organization that is paying.
     * @return The payment made.
     */
    public Payment makeBankTransfer(Organization organization) {
        return AppPOE.getInstance().getApp().getPaymentAPI().payTo(getAmount(), getFreelancerIBAN());
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
    private Double getAmount() {
        // TODO: complete!!!!!
        return 1.0;
    }
}
