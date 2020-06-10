/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.model;

import autorizacao.AuthFacade;
import lapr.api.EmailAPI;
import lapr.api.PswGeneratorAPI;
import lapr.controller.AppPOE;
import lapr.regist.RegistFreelancer;
import lapr.regist.RegistOrganization;
import lapr.api.MonetaryConversionAPI;
import lapr.api.PaymentAPI;

import java.io.*;

/**
 * @author André Botelho and Ricardo Moreira.
 */
public class App implements Serializable {
    /**
     * The autorization facade used by the app.
     */
    private final AuthFacade m_oAutorization;
    /**
     * The registration of organization on the app.
     */
    private RegistOrganization m_oRegistOrganization;
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
    /**
     * The object responsible for sending emails to freelancers every year.
     */
    private EmailScheduler m_oEmailScheduler;


    public App() {
        this.m_oAutorization = new AuthFacade();
        this.m_oRegistOrganization = new RegistOrganization();
        this.m_oRegistFreelancer = new RegistFreelancer();
        this.m_oEmailScheduler = new EmailScheduler();
    }

    /**
     * @return The autorization facade used by the app.
     */
    public AuthFacade getAuthFacade()
    {
        return this.m_oAutorization;
    }

    public RegistOrganization getRegistOrganization() {
        return m_oRegistOrganization;
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

    /**
     * Serialize data.
     * @param path The path of the file to serialize the data to.
     * @throws IOException If the file is not able to be opened or serialization fails.
     */
    public void serialize(final String path) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    /**
     * Deserializes data from file.
     * @param path The path of the file to deserialize the data from.
     * @return The deserialized data.
     * @throws IOException If the file is not able to be opened or serialization fails.
     * @throws ClassNotFoundException If class of a serialized object cannot be found.
     */
    public static App deserialize(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream out = new ObjectInputStream(fileIn);
        final App app = (App) out.readObject();
        out.close();
        fileIn.close();
        return app;
    }
}
    
    
