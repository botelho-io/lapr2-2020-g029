/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.list.ListTransaction;

/**
 * Represents ans organazation seeking freelancers to complete tasks.
 */
public class Organization {
    /**
     * Name of the organization.
     */
    private String m_strName;
    /**
     * Responsible for making payments on unpaid transactions.
     */
    PaymentScheduler m_oScheduler;
    /**
     * A list of unpaid transactions.
     */
    ListTransaction m_oListTransaction;
    /**
     * @return The list of unpaid transactions the organization needs to pay.
     */
    public ListTransaction getListTransaction() {
        return m_oListTransaction;
    }
}
