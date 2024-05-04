/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.enums;

import java.util.HashMap;

public enum ResponseType {

    TEXT,
    OPTIONS;

    public static HashMap getAll(){
        HashMap<String, ResponseType> alertTypes = new HashMap<>();
        for(ResponseType alertType : ResponseType.values()){
            alertTypes.put(alertType.toString().toLowerCase(), alertType);
        }

        return alertTypes;
    }

}
