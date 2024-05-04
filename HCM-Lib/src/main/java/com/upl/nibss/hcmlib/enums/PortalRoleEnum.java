/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upl.nibss.hcmlib.enums;

/**
 *
 * @author Gbenga
 */
public enum PortalRoleEnum {

    ADMIN("ADMIN"),
    USER("USER");

    private PortalRoleEnum(String strValue) {
        this.value = strValue;
    }

    private String value;

    @Override
    public String toString() {
        return value;
    }

}
