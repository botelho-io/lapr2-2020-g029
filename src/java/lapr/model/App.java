/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.model;

import autorizacao.AuthFacade;
import lapr.api.EmailAPI;
import lapr.api.PswGeneratorAPI;
import lapr.regist.RegistFreelancer;
import lapr.regist.RegistOrganization;
import lapr.api.MonetaryConversionAPI;
import lapr.api.PaymentAPI;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {
    /**
     * The autorization facade used by the app.
     */
    private final AuthFacade m_oAutorizacao;
    /**
     * The registration of organization on the app.
     */
    private RegistOrganization ro;
    /**
     * The API used to generate passwords.
     */
    private PswGeneratorAPI m_oPswGeneratorAPI;
    /**
     * The API used to process bank payments.
     */
    private PaymentAPI m_oPaymentAPI;
    /**
     * The API used to convert between monetary units.
     */
    private MonetaryConversionAPI m_oMonetaryConversionAPI;
    /**
     * The API used to send emails.
     */
    private EmailAPI m_oEmailAPI;
    /**
     * The register of all the freelancers available in the system.
     */
    private RegistFreelancer m_oRegistFreelancer;

    public App()
    {
        this.m_oAutorizacao = new AuthFacade();
        this.ro = new RegistOrganization();
        this.m_oRegistFreelancer = new RegistFreelancer();
    }
    /**
     * @return The autorization facade used by the app.
     */
    public AuthFacade getAutorizacaoFacade()
    {
        return this.m_oAutorizacao;
    }

    public RegistOrganization getRegistOrganization() {
        return ro;
    }

    public PswGeneratorAPI getPswGeneratorAPI() {
        return this.m_oPswGeneratorAPI;
    }

    /**
     * @return An instance of the payment API.
     */
    public PaymentAPI getPaymentAPI() {
        return m_oPaymentAPI;
    }

    public MonetaryConversionAPI getMonetaryConversionAPI() {
        return this.m_oMonetaryConversionAPI;
    }

    /**
     * @return The API used to send emails.
     */
    public EmailAPI getEmailAPI() {
        return this.m_oEmailAPI;
    }

    /**
     * @return The register of all the freelancers available in the system.
     */
    public RegistFreelancer getRegistFreelancer() {
        return this.m_oRegistFreelancer;
    }

    /**
     * Sets the APIs supported by the application.
     * @param email The API used to send emails.
     * @param conversion The API used to convert between monetary units.
     * @param payment The API used to process bank payments.
     * @param pswGenerator The API used to generate passwords.
     */
    public void setAPIs(EmailAPI email, MonetaryConversionAPI conversion, PaymentAPI payment, PswGeneratorAPI pswGenerator) {
        this.m_oMonetaryConversionAPI = conversion;
        this.m_oEmailAPI = email;
        this.m_oPaymentAPI = payment;
        this.m_oPswGeneratorAPI = pswGenerator;
    }
}
    
    
