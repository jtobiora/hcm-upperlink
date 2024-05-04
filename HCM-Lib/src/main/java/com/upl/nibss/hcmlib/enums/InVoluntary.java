package com.upl.nibss.hcmlib.enums;

public enum InVoluntary {
    RE_ORGANISATION("Re-Organisation"),
    REDUNDANCY("Redundancy"),
    LAY_OFF("Lay Off"),
    VIOLATION_OF_POLICIES("Violation of the Company’s Policies"),
    POOR_PERFOMANCE("Poor Performance"),
    OTHER("");
    private String value;

    InVoluntary(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
