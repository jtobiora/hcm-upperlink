package com.upl.nibss.hcmlib.enums;

public enum Voluntary {
    ANOTHER_POSITION("Another Position"),
    RELOCATION("Relocation"),
    RETURN_TO_SCHOOL("Return to school"),
    FAMILY_OR_PERSONAL_ISSUES("Family/Personal issues"),
    RETIREMENT("Retirement"),
    RESIGNATION("Resignation"),
    OTHER("");

    private String value;

    Voluntary(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
