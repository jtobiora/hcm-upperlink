package com.upl.nibss.hcmlib.dto.authsetting;

import lombok.Data;

/**
 * Created by toyin.oladele on 31/10/2017.
 */
@Data
public class PasswordSettingRequest {

    private String passwordLength = "0";
    private String minSpecialChar = "0";
    private String minLowerCase = "0";
    private String minUpperCase = "0";
    private String minDigit = "0";

}
