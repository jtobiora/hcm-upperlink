package com.upl.nibss.hcm.dto;

import lombok.Data;

/**
 * Created by toyin.oladele on 25/12/2017.
 */
@Data
public class PasswordSetting {
    private int passwordLength;
    private int minSpecialChar;
    private int minLowerCase;
    private int minUppercase;
    private int minDigit;
}
