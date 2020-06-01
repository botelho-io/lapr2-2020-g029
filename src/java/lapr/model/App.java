/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.model;

import autorizacao.AutorizacaoFacade;
import lapr.api.EmailAPI;
import lapr.api.PswGeneratorAPI;
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
    private final AutorizacaoFacade m_oAutorizacao;
    private RegistOrganization ro;
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

    public App()
    {
        this.m_oAutorizacao = new AutorizacaoFacade();
        this.ro = new RegistOrganization();
    }
    /**
     * @return The autorization facade used by the app.
     */
    public AutorizacaoFacade getAutorizacaoFacade()
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
}
    
    
