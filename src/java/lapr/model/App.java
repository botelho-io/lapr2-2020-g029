/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.model;

import autorizacao.AutorizacaoFacade;
import lapr.api.PaymentAPI;
import lapr.regist.RegistPayment;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {
    /**
     * The autorization facade used by the app.
     */
    private final AutorizacaoFacade m_oAutorizacao;
    /**
     * The payments registered in the app.
     */
    private RegistPayment m_oRegistPayment;
    /**
     * The API used to process bank payments.
     */
    private PaymentAPI m_oPaymentAPI;

    public App()
    {
        this.m_oAutorizacao = new AutorizacaoFacade();
    }
    /**
     * @return The autorization facade used by the app.
     */
    public AutorizacaoFacade getAutorizacaoFacade()
    {
        return this.m_oAutorizacao;
    }
    /**
     * @return The payments registered in the app.
     */
    public RegistPayment getRegistPayment() {
        return this.m_oRegistPayment;
    }
    /**
     * @return An instance of the payment API.
     */
    public PaymentAPI getPaymentAPI() {
        return m_oPaymentAPI;
    }
}
    
    
