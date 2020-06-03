/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.utils.Expertise;

import java.util.Objects;

/**
 * Represents a freelancer that intends do profit from completing tasks.
 */
public class Freelancer {
    /**
     * The unique ID of this freelancer.
     */
    private String m_strId;
    /**
     * The name of this freelancer.
     */
    private String m_strName;
    /**
     * The level of expertise of this freelancer.
     */
    private Expertise m_strLevelOfExpertise;
    /**
     * The unique email of this freelancer.
     */
    private String m_strEmail;
    /**
     * The NIF of this freelancer.
     */
    private String m_strNIF;
    /**
     * Freelancer's International Bank Account Number.
     */
    private String m_strBankAccountIBAN;
    /**
     * The address of this freelancer.
     */
    private String m_strAddress;
    /**
     * The country where the freelancer resides.
     */
    private String m_strCountry;

    /**
     * Constructor.
     * @param strId The unique ID of this freelancer.
     * @param strName The name of this freelancer.
     * @param enumExpertise The level of expertise of this freelancer.
     * @param strEmail The unique email of this freelancer.
     * @param strNIF The NIF of this freelancer.
     * @param strIBAN Freelancer's International Bank Account Number.
     * @param strAddress The address of this freelancer.
     * @param strCountry The country where the freelancer resides.
     */
    public Freelancer(String strId, String strName, Expertise enumExpertise, String strEmail, String strNIF, String strIBAN, String strAddress, String strCountry) {
        this.m_strBankAccountIBAN = strIBAN;
        this.m_strEmail = strEmail;
        this.m_strId = strId;
        this.m_strLevelOfExpertise = enumExpertise;
        this.m_strName = strName;
        this.m_strNIF = strNIF;
        this.m_strAddress = strAddress;
        this.m_strCountry = strCountry;
    }

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
        return m_strEmail;
    }
    /**
     * @return The country where the freelancer resides.
     */
    public String getCountry() {
        return m_strCountry;
    }
    /**
     * @return The unique ID of this freelancer.
     */
    public String getId() {
        return m_strId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Freelancer)) return false;
        Freelancer that = (Freelancer) o;
        return m_strId.equals(that.m_strId) ||
                m_strEmail.equals(that.m_strEmail) ||
                m_strNIF.equals(that.m_strNIF) ||
                m_strBankAccountIBAN.equals(that.m_strBankAccountIBAN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_strId, m_strEmail, m_strNIF, m_strBankAccountIBAN);
    }
}
