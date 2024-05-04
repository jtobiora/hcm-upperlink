/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.enums;

import java.util.HashMap;

/**
 *
 * @author Gbenga
 */
public enum AlertType {

    AUTHORIZE,
    AWARENESS;

    public static HashMap getAll(){
        HashMap<String, AlertType> alertTypes = new HashMap<>();
        for(AlertType alertType : AlertType.values()){
            alertTypes.put(alertType.toString().toLowerCase(), alertType);
        }

        return alertTypes;
    }

}
