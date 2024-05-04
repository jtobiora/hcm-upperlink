package com.upl.nibss.hcm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by toyin.oladele on 25/12/2017.
 */
@Data
public class Password {

    @JsonProperty("new_password")
    private String newPassword;

}
