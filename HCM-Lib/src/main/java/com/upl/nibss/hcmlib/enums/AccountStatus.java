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
public enum AccountStatus {

    ACTIVE("Active"),
    SUSPENDED("Suspended");

    AccountStatus(String status) {
        accountStatus = status;
    }

    private final String accountStatus;

    @Override
    public String toString() {
        return accountStatus;
    }

    public static HashMap getAll(){
        HashMap<String, AccountStatus> accountStatusHashMap = new HashMap<>();
        for(AccountStatus accountStatus : AccountStatus.values()){
            accountStatusHashMap.put(accountStatus.toString().toLowerCase(), accountStatus);
        }

        return accountStatusHashMap;
    }
}
