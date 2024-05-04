package com.upl.nibss.hcm.dto.login;

import com.upl.nibss.hcmlib.dto.SuperResponse;
import lombok.Data;

/**
 * Created by toyin.oladele on 18/10/2017.
 */
@Data
public class LoginResponse extends SuperResponse {

    private String token;

}
