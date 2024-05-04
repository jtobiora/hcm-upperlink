package com.upl.nibss.hcm.enums;

/**
 * Created by toyin.oladele on 13/10/2017.
 */
public enum AuthError {

    INVALID_REQUEST("Invalid request"),
    INVALID_USER("Invalid username/password."),
    DISABLED_USER("User is disabled"),
    DELETED_USER("User is deleted"),
    EXPIRED_TOKEN("Token is expired"),
    MISSING_TOKEN("Token not found"),
    WRONG_USER("Wrong User provided"),
    ALREADY_LOGGED_IN("User is already logged in");

    private String value;

    AuthError(String message) {
        this.value = message;
    }

    public String getValue() {
        return value;
    }
}
