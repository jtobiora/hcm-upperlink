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
public enum GenderType {

    MALE,
    FEMALE;

    public static HashMap getAll(){
        HashMap<String, GenderType> genderTypes = new HashMap<>();
        for(GenderType genderType : GenderType.values()){
            genderTypes.put(genderType.toString().toLowerCase(), genderType);
        }

        return genderTypes;
    }
}
