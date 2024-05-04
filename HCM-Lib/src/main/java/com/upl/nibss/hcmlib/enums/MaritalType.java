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
public enum MaritalType {

    SINGLE,
    MARRIED,
    DIVORCED,
    SEPARATED,
    WIDOWED;

    public static HashMap getAll(){
        HashMap<String, MaritalType> maritalTypes = new HashMap<>();
        for(MaritalType maritalType : MaritalType.values()){
            maritalTypes.put(maritalType.toString().toLowerCase(), maritalType);
        }

        return maritalTypes;
    }

}
