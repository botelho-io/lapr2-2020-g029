/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lapr.api.EmailAPI;
import lapr.api.MonetaryConversionAPI;
import lapr.api.PaymentAPI;
import lapr.api.PswGeneratorAPI;
import lapr.model.*;
import lapr.utils.Constants;

public class AppPOE {
    private static AppPOE singleton = null;
    private final App m_oApp;

    private AppPOE() {
        this(new App());
    }

    private AppPOE(App app) {
        this.m_oApp = app;
        Properties props = getProperties();
    }

    public static void setApp(App app) throws IOException {
        if(singleton != null) {
            singleton.getApp().close();
        }
        singleton = new AppPOE(app);
    }

    public App getApp() {
        return this.m_oApp;
    }

    private Properties getProperties() {
        Properties props = new Properties();

        // Default values
        props.setProperty("admin.name", Constants.defaultAdminName);
        props.setProperty("admin.email", Constants.defaultAdminEmail);
        props.setProperty("admin.password", Constants.defaultAdminPassword);
        props.setProperty("api.email", Constants.defaultApiEmail);
        props.setProperty("api.monetaryConversion", Constants.defaultApiMonetaryConversion);
        props.setProperty("api.payment", Constants.defaultApiPayment);
        props.setProperty("api.passwordGenerator", Constants.defaultApiPasswordGenerator);

        try {
            InputStream in = new FileInputStream(Constants.pathPropertiesFile);
            props.load(in);
            in.close();
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            // TODO Handle Exception.
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return props;
    }

    private void bootstrap() {
        // Get Properties and APIs
        Properties p = reloadAPIs();

        // Add Admin
        final Administrator adm = new Administrator(p.getProperty("admin.name"), p.getProperty("admin.email"), p.getProperty("admin.password"));
        getApp().getAuthFacade().registerUser(adm);

        // Add test data TODO: Delete
        // TestConstants.addTestOrgTasksFreelancersAndTransactions();
    }

    public Properties reloadAPIs() {
        Properties p = getProperties();

        // Add APIs
        final EmailAPI email_api = tryToGetFromName(p.getProperty("api.email"));
        final MonetaryConversionAPI money_api = tryToGetFromName(p.getProperty("api.monetaryConversion"));
        final PaymentAPI pay_api = tryToGetFromName(p.getProperty("api.payment"));
        final PswGeneratorAPI psw_api = tryToGetFromName(p.getProperty("api.passwordGenerator"));
        m_oApp.setAPIs(email_api, money_api, pay_api, psw_api);

        return p;
    }

    private static<T> T tryToGetFromName(final String name) {
        try {
            return (T) (Class.forName(name).getConstructor().newInstance());
        } catch (Exception e) {
            // TODO: Handle exception
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }

    public static AppPOE getInstance() {
        if(singleton == null) {
            singleton = new AppPOE();
            singleton.bootstrap();
        }
        return singleton;
    }

    /**
     * Restarts the instance of AppPOE, useful for testing.
     */
    public static void restartInstance() throws IOException {
        synchronized(AppPOE.class) {
            if(singleton != null)
                singleton.getApp().close();
            singleton = new AppPOE();
            singleton.bootstrap();
        }
    }
}
