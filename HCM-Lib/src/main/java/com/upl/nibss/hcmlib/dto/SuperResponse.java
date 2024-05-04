package com.upl.nibss.hcmlib.dto;

import lombok.Data;

/**
 * Created by toyin.oladele on 12/10/2017.
 */
@Data
public class SuperResponse {

    public boolean status;
    public String message;
    public Object data;

}
