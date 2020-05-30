/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import autorizacao.model.PapelUtilizador;
import main.controller.AppPOE;
import main.utils.Constants;

/**
 *
 * @author Universidade
 */
public class Administrator extends User {

    public Administrator (String name, String email, String password, PapelUtilizador[] papeis){
        super(name, email, password, papeis);
    }

    public Administrator (String name, String email, String password){
        this(name, email, password, new PapelUtilizador[]{AppPOE.getInstance().getRole(Constants.ROLE_MANAGER)});
    }
}
