package com.upl.nibss.hcmlib.enums;

/**
 * Created by toyin.oladele on 09/12/2017.
 */
public enum LeaveBookingStatus {

    BOOKED("#5cb85c","fa fa-book"),
    NON_LEAVE_DAYS("#a94442","fa fa-ban"),
    EXPIRED("#a94442","fa fa-ban"),
    PENDING("#FF9233","fa fa-spinner"),
    ONGOING("#FF9233","fa fa-spinner"),
    USED("#0bb2ff","fa fa-check-circle-o");

    private String color;
    private String icon;

    LeaveBookingStatus(String color, String icon) {
        this.color = color;
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }
}
