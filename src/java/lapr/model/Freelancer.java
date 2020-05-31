/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.utils.Expertise;

/**
 * Represents a freelancer that intends do profit from completing tasks.
 */
public class Freelancer {
    private String id;
    private String name;
    /**
     * The level of expertise of this freelancer.
     */
    private Expertise m_strLevelOfExpertise;
    /**
     * The unique email of this freelancer.
     */
    private String email;
    private String NIF;
    /**
     * Freelancer's International Bank Account Number.
     */
    private String m_strBankAccountIBAN;
    private String address;
    /**
     * The country where the freelancer resides.
     */
    private String country;
    /**
     * @return The freelancer's International Bank Account Number.
     */
    public String getIBAN() {
        return m_strBankAccountIBAN;
    }
    /**
     * @return The level of expertise of this freelancer.
     */
    public Expertise getLevelOfExpertise() {
        return m_strLevelOfExpertise;
    }
    /**
     * @return The unique email of this freelancer.
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return The country where the freelancer resides.
     */
    public String getCountry() {
        return country;
    }
}
