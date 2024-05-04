package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.enums.AuthMode;

/**
 * Created by toyin.oladele on 30/10/2017.
 */
public interface IAuthModeService {

    AuthMode get() throws Exception;

    AuthMode create(AuthMode authMode) throws Exception;

    AuthMode update(AuthMode authMode) throws Exception;

}
