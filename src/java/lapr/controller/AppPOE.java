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

public class AppPOE {
    private static AppPOE singleton = null;

    private final App m_oApp;

    private AppPOE() {
        Properties props = getProperties();
        this.m_oApp = new App();
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
        Constants.addTestOrgTasksFreelancersAndTransactions();
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
