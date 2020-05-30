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

import autorizacao.model.PapelUtilizador;
import lapr.model.Administrator;
import lapr.model.App;
import lapr.utils.Constants;
import autorizacao.AutorizacaoFacade;
import autorizacao.model.SessaoUtilizador;

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
        this.m_oAutorizacao.registaPapelUtilizador(Constants.ROLE_ADMINISTRATOR);
        this.m_oAutorizacao.registaPapelUtilizador(Constants.ROLE_COLABORATOR);
        this.m_oAutorizacao.registaPapelUtilizador(Constants.ROLE_MANAGER);

        Administrator adm = new Administrator("Admin Joe", "admin@dei.pt", "password", new PapelUtilizador[]{getRole(Constants.ROLE_ADMINISTRATOR)});
        this.m_oAutorizacao.registaUtilizador(adm);

    }

    // Inspirado em https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static AppPOE singleton = null;
    public static AppPOE getInstance()
    {
        if(singleton == null)
        {
            synchronized(AppPOE.class)
            {
                singleton = new AppPOE();
            }
        }
        return singleton;
    }


    public PapelUtilizador getRole(String role) {
        return this.m_oAutorizacao.getRole(role);
    }
}
