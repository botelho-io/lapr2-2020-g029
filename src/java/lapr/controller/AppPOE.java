/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

import autorizacao.model.UserRole;
import lapr.api.stubs.StubEmailAPI;
import lapr.api.stubs.StubMonetaryConversionAPI;
import lapr.api.stubs.StubPaymentAPI;
import lapr.api.stubs.StubPswGeneratorAPI;
import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.model.*;
import lapr.utils.Constants;
import autorizacao.AuthFacade;
import autorizacao.model.SessaoUtilizador;
import lapr.utils.Expertise;
import lapr.utils.Role;

/**
 *
 * @author paulomaio
 */
public class AppPOE
{

    private final App m_oApp;
    private final AuthFacade m_oAutorization;

    private AppPOE()
    {
        Properties props = getProperties();
        this.m_oApp = new App();
        this.m_oAutorization = this.m_oApp.getAutorizacaoFacade();
    }

    public App getApp()
    {
        return this.m_oApp;
    }

    public AuthFacade getAuthFacade() {
        return m_oAutorization;
    }

    public SessaoUtilizador getSessaoAtual()
    {
        return this.m_oAutorization.getSessaoAtual();
    }

    public boolean doLogin(String strId, String strPwd)
    {
        return this.m_oAutorization.doLogin(strId,strPwd) != null;
    }

    public void doLogout()
    {
        this.m_oAutorization.doLogout();
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Adiciona propriedades e valores por omissão


        // Lê as propriedades e valores definidas
        // TODO: Isto é util?
        try
        {
            InputStream in = new FileInputStream(Constants.PATH_PARAMS);
            props.load(in);
            in.close();
        }
        catch(IOException ignored)
        {

        }
        return props;
    }


    private void bootstrap()
    {
        // Add roles
        this.m_oAutorization.registaPapelUtilizador(Role.ADMINISTRATOR);
        this.m_oAutorization.registaPapelUtilizador(Role.COLLABORATOR);
        this.m_oAutorization.registaPapelUtilizador(Role.MANAGER);

        // Add APIs
        // TODO: add real APIs
        m_oApp.setAPIs(new StubEmailAPI(), new StubMonetaryConversionAPI(), new StubPaymentAPI(), new StubPswGeneratorAPI());

        // Add test data TODO: Delete
        Constants.addTestOrgTasksFreelancersAndTransactions();
        // new SendEmailTask(new EmailScheduler()).run(); // Send test emails
    }

    // Inspirado em https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static AppPOE singleton = null;
    public static AppPOE getInstance()
    {
        if(singleton == null)
        {
            restartInstance();
        }
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


    public UserRole getRole(Role role) {
        return this.m_oAutorization.getRole(role);
    }
}
