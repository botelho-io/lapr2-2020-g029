/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.lapr2.t4j.main.model;

import pt.ipp.isep.dei.lapr2.t4j.main.controller.AppPOE;
import pt.ipp.isep.dei.lapr2.t4j.main.utils.Constants;

/**
 *
 * @author Universidade
 */
public class Manager extends User {
    
    public Manager (String name, String email, String password){
        super(name, email, password, AppPOE.getInstance().getRole(Constants.ROLE_MANAGER));
    }

}
