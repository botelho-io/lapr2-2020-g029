/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Collator;
import java.util.Properties;

import autorizacao.model.PapelUtilizador;
import lapr.api.stubs.StubEmailAPI;
import lapr.api.stubs.StubMonetaryConversionAPI;
import lapr.api.stubs.StubPaymentAPI;
import lapr.api.stubs.StubPswGeneratorAPI;
import lapr.model.Administrator;
import lapr.model.App;
import lapr.model.Collaborator;
import lapr.utils.Constants;
import autorizacao.AutorizacaoFacade;
import autorizacao.model.SessaoUtilizador;
import lapr.utils.Role;

/**
 *
 * @author paulomaio
 */
public class AppPOE
{

    private final App m_oApp;
    private final AutorizacaoFacade m_oAutorizacao;

    private AppPOE()
    {
        Properties props = getProperties();
        this.m_oApp = new App();
        this.m_oAutorizacao = this.m_oApp.getAutorizacaoFacade();
        bootstrap();
    }

    public App getApp()
    {
        return this.m_oApp;
    }


    public SessaoUtilizador getSessaoAtual()
    {
        return this.m_oAutorizacao.getSessaoAtual();
    }

    public boolean doLogin(String strId, String strPwd)
    {
        return this.m_oAutorizacao.doLogin(strId,strPwd) != null;
    }

    public void doLogout()
    {
        this.m_oAutorizacao.doLogout();
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
        this.m_oAutorizacao.registaPapelUtilizador(Role.ADMINISTRATOR);
        this.m_oAutorizacao.registaPapelUtilizador(Role.COLLABORATOR);
        this.m_oAutorizacao.registaPapelUtilizador(Role.MANAGER);

        Administrator adm = new Administrator("Admin Joe", "admin@dei.pt", "password", new PapelUtilizador[]{getRole(Role.ADMINISTRATOR)});
        this.m_oAutorizacao.registaUtilizador(adm);
        Collaborator col = new Collaborator("Colab Joe", "colab@dei.pt", "password", new PapelUtilizador[]{getRole(Role.COLLABORATOR)});
        this.m_oAutorizacao.registaUtilizador(col);
        // TODO: add real APIs
        m_oApp.setAPIs(new StubEmailAPI(), new StubMonetaryConversionAPI(), new StubPaymentAPI(), new StubPswGeneratorAPI());
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
        synchronized(AppPOE.class)
        {
            singleton = new AppPOE();
        }
    }


    public PapelUtilizador getRole(Role role) {
        return this.m_oAutorizacao.getRole(role);
    }
}
