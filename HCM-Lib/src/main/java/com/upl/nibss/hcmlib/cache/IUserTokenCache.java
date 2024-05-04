package com.upl.nibss.hcmlib.cache;

import java.util.List;

/**
 * Created by toyin.oladele on 15/10/2017.
 */
public interface IUserTokenCache {

    Object findUserToken(String userToken, String sessionId);

    boolean saveUserToken(String userToken, String sessionId);

    boolean saveUserTokenAndTask(String sessionId, String userToken, List<String> tasks);

    List<String> getTask(String sessionId, String userToken);

    void deleteUserToken(String userToken, String sessionId);

}
