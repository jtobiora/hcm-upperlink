package com.upl.nibss.hcm.enums;

public enum Errors {

    INVALID_DATA("Invalid {} provided."),
    UNKNOWN_JOBROLE_ID("unknown job role id."),
    UNKNOWN_DEPARTMENT_ID("unknown department id."),
    UNKNOWN_EMPLOYEE_ID("unknown employee id."),
    UNKNOWN_EMPLOYEE_SUPERVISOR_ID("unknown employee supervisor id."),
    UNKNOWN_EMPLOYEE_SECOND_LEVEL_SUPERVISOR_ID("unknown employee second level supervisor id."),
    UNKNOWN_GRADE_LEVEL_ID("Unknown grade level id"),
    EMPLOYEE_NO_ALREADY_EXIST("Employee no '{}' already exist"),
    PRIORITY_ALREADY_EXIST("Priority '{}' already exist"),
    NOT_PERMITTED("Permission not granted."),
    NO_GROUP_FOUND("No Group found, Employee must belong to at least a group"),
    INVALID_REQUEST("Invalid request provided."),
    UNKNOWN_USER("unknown user."),
    UNKNOWN_DATA("unknown {}."),
    EXPIRED_SESSION("Expired Session"),
    EXPIRED_TOKEN("Expired Token"),
    ID_IS_NULL("Id is NULL"),
    DATA_NOT_PROVIDED("{} is not provided"),
    OLD_AND_NEW_DATA_CANT_BE_SAME("Old {} and New {} cannot be the same."),
    DATA_DATE_MUST_BE_AFTER_TODAY("{} is not allowed, Please provide a {} after today."),
    DATA_DATE_MUST_BE_A_VALID_RANGE("Start date and end date must be of a valid date range."),
    DATA_AGE_MUST_BE_A_VALID_RANGE("Min age and Min age must be of a valid age range."),
    INVALID_DATA_PROVIDED("Invalid {} provided");


    private String value;

    Errors(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
