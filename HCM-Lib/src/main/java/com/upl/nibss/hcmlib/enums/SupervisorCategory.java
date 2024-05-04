package com.upl.nibss.hcmlib.enums;

public enum SupervisorCategory {

    NONE(null),
    SUPERVISOR("SUPERVISOR"),
    SECOND_LEVEL_SUPERVISOR("SECOND_LEVEL_SUPERVISOR");



    private String value;

    SupervisorCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
