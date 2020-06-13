/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.model;

import authorization.AuthFacade;
import lapr.api.EmailAPI;
import lapr.api.PswGeneratorAPI;
import lapr.controller.AppPOE;
import lapr.api.MonetaryConversionAPI;
import lapr.api.PaymentAPI;
import lapr.lists.RegistFreelancer;
import lapr.lists.RegistOrganization;

import java.io.*;

/**
 * @author André Botelho and Ricardo Moreira.
 */
public class App implements Serializable, Closeable{
    /**
     * The autorization facade used by the app.
     */
    private AuthFacade m_oAutorization;
    /**
     * The registration of organization on the app.
     */
    private RegistOrganization m_oRegistOrganization;
    /**
     * The register of all the freelancers available in the system.
     */
    private RegistFreelancer m_oRegistFreelancer;
    /**
     * The object responsible for sending emails to freelancers every year.
     */
    private EmailScheduler m_oEmailScheduler;
    /**
     * The API used to generate passwords.
     */
    private transient PswGeneratorAPI m_oPswGeneratorAPI;
    /**
     * The API used to process bank payments.
     */
    private transient PaymentAPI m_oPaymentAPI;
    /**
     * The API used to convert between monetary units.
     */
    private transient MonetaryConversionAPI m_oMonetaryConversionAPI;
    /**
     * The API used to send emails.
     */
    private transient EmailAPI m_oEmailAPI;

    public App() {
        this.m_oAutorization = new AuthFacade();
        this.m_oRegistOrganization = new RegistOrganization();
        this.m_oRegistFreelancer = new RegistFreelancer();
        this.m_oEmailScheduler = new EmailScheduler();
    }

    /**
     * Read object.
     * @param aInputStream The input stream.
     * @throws ClassNotFoundException If the APIs fail to load.
     * @throws IOException If the file cannot be deserialized.
     */
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        this.m_oAutorization = (AuthFacade) aInputStream.readObject();
        this.m_oRegistOrganization = (RegistOrganization) aInputStream.readObject();
        this.m_oRegistFreelancer = (RegistFreelancer) aInputStream.readObject();
        this.m_oEmailScheduler = (EmailScheduler) aInputStream.readObject();
        AppPOE.getInstance().reloadAPIs();
    }

    /**
     * Writes the object.
     * @param aOutputStream The output stream.
     * @throws IOException If the file cannot be whiten to.
     */
    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeObject(m_oAutorization);
        aOutputStream.writeObject(m_oRegistOrganization);
        aOutputStream.writeObject(m_oRegistFreelancer);
        aOutputStream.writeObject(m_oEmailScheduler);
    }

    /**
     * Returns the autorization facade used by the app.
     * @return he autorization facade used by the app.
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
     * Returns an instance of the payment API.
     * @return An instance of the payment API.
     */
    public PaymentAPI getPaymentAPI() {
        return m_oPaymentAPI;
    }

    public MonetaryConversionAPI getMonetaryConversionAPI() {
        return this.m_oMonetaryConversionAPI;
    }

    /**
     * Returns the API used to send emails.
     * @return he API used to send emails.
     */
    public EmailAPI getEmailAPI() {
        return this.m_oEmailAPI;
    }

    /**
     * Returns the register of all the freelancers available in the system.
     * @return he register of all the freelancers available in the system.
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

    @Override
    public void close() throws IOException {
        m_oEmailAPI.close();
        m_oMonetaryConversionAPI.close();
        m_oPaymentAPI.close();
        m_oPswGeneratorAPI.close();
    }
}


