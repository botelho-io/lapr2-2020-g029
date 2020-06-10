/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lapr.api.stubs.StubEmailAPI;
import lapr.api.stubs.StubMonetaryConversionAPI;
import lapr.api.stubs.StubPaymentAPI;
import lapr.api.stubs.StubPswGeneratorAPI;
import lapr.model.*;
import lapr.utils.Constants;
import autorizacao.AuthFacade;
import lapr.utils.Role;
import lapr.utils.TestConstants;

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

    public static void setApp(App app) {
        singleton = new AppPOE(app);
    }

    public App getApp() {
        return this.m_oApp;
    }

    private Properties getProperties() {
        Properties props = new Properties();
        // TODO: Is this useful?
        try {
            InputStream in = new FileInputStream(Constants.PATH_PARAMS);
            props.load(in);
            in.close();
        } catch(IOException ignored) {
        }
        return props;
    }

    private void bootstrap() {
        // Add roles
        AuthFacade auth = getApp().getAuthFacade();
        auth.registaPapelUtilizador(Role.ADMINISTRATOR);
        auth.registaPapelUtilizador(Role.COLLABORATOR);
        auth.registaPapelUtilizador(Role.MANAGER);

        // Add APIs
        // TODO: add real APIs
        m_oApp.setAPIs(new StubEmailAPI(), new StubMonetaryConversionAPI(), new StubPaymentAPI(), new StubPswGeneratorAPI());

        // Add test data TODO: Delete
        TestConstants.addTestOrgTasksFreelancersAndTransactions();
        // new SendEmailTask(new EmailScheduler()).run(); // Send test emails
    }

    public static AppPOE getInstance() {
        if(singleton == null)
            restartInstance();
        return singleton;
    }

    /**
     * Restarts the instance of AppPOE, useful for testing.
     */
    public static void restartInstance() {
        synchronized(AppPOE.class) {
            singleton = new AppPOE();
            singleton.bootstrap();
        }
    }
}
