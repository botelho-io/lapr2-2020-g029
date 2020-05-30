/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model;

import lapr.controller.AppPOE;
import lapr.utils.Constants;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Universidade
 */
public class Organization {

    private String name;
    private Manager manager;
    private Colaborator colaborator;
    private final Set<Colaborator>  listColaborator;

    public Organization(String name, Manager manager, Colaborator colaborator) {
        if ((name == null) || (manager == null) || (colaborator == null))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
            this.name = name;
            this.manager = manager;
            this.colaborator = colaborator;
            this.listColaborator = new HashSet<>();
    }


    public static Colaborator newColaborator (String name, String email, String password) {
        return new Colaborator(name,email,password);
    }

    public static Manager newManager (String name, String email, String password) {
        return new Manager(name,email,password);
    }

    public boolean validatesColaborator(Colaborator colaborator) {
        //Escrever codigo validacao do colaborador
        return true;
    }

    public boolean registerColaborador(Colaborator colaborator)
    {
        if (this.validatesColaborator(colaborator))
        {

            String nameC = colaborator.getNome();
            String emailC = colaborator.getEmail();
            String passwordC = colaborator.getPassword();
            if (AppPOE.getInstance().getApp().getAutorizacaoFacade().registaUtilizadorComPapel(nameC, emailC, passwordC, AppPOE.getInstance().getRole(Constants.ROLE_COLABORATOR)); {
                return addColaborador(colaborator);
            }
        }
        return false;

    }

    private boolean addColaborador(Colaborator colaborator) {
        return listColaborator.add(colaborator);
    }

    public boolean validatesManager(Manager manager) {
        //Escrever codigo validacao do colaborador
        return true;
    }

    public boolean registerManager(Manager manager)
    {
        if (this.validatesManager(manager))
        {

            String nomeM = manager.getNome();
            String emailM = manager.getEmail();
            String passwordM = manager.getPassword();
            if (AppPOE.getInstance().getApp().getAutorizacaoFacade().registaUtilizadorComPapel(nomeM, emailM, passwordM, AppPOE.getInstance().getRole(Constants.ROLE_MANAGER));{
                return addManager(manager);
            }
        }
        return false;

    }

    private boolean addManager(Manager manager) {
        return listColaborator.add(colaborator);
    }

    public Set<Colaborator> getListaColaboradores() {
            return listColaborator;
        }
}