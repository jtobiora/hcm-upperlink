package com.upl.nibss.hcmlib.cache;

/**
 * Created by toyin.oladele on 23/10/2017.
 */
public interface ISessionManager {

    void updateTimeout(String sessionId);

    boolean isValidateSession(String sessionId);

    void deleteSession(String sessionId);

}
