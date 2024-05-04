package com.upl.nibss.hcmlib.enums;

public enum RotationCategory {
    NONE(null),
    SENDING_SUPERVISOR("SENDING_SUPERVISOR"),
    SENDING_DEPARTMENT_HEAD("SENDING_DEPARTMENT_HEAD"),
    SENDING_EXECUTIVE_DEPARTMENT("SENDING_EXECUTIVE_DEPARTMENT"),

    RECEIVING_SUPERVISOR("RECEIVING_SUPERVISOR"),
    RECEIVING_DEPARTMENT_HEAD("RECEIVING_DEPARTMENT_HEAD"),
    RECEIVING_EXECUTIVE_DEPARTMENT("RECEIVING_EXECUTIVE_DEPARTMENT");

    private String value;

    RotationCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
