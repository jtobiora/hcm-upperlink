package com.upl.nibss.hcmlib.enums;

import java.util.HashMap;

public enum ResponseStatus {

    REQUIRED,
    OPTIONAL;

    public static HashMap getAll(){
        HashMap<String, ResponseStatus> alertTypes = new HashMap<>();
        for(ResponseStatus alertType : ResponseStatus.values()){
            alertTypes.put(alertType.toString().toLowerCase(), alertType);
        }

        return alertTypes;
    }

}
