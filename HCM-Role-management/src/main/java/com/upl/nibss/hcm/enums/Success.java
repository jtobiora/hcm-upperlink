package com.upl.nibss.hcm.enums;

/**
 * Created by toyin.oladele on 23/11/2017.
 */
public enum Success {

    SUCCESS("Successful");

    private String value;

    Success(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
