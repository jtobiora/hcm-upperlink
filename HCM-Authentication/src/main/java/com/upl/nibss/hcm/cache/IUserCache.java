package com.upl.nibss.hcm.cache;

import com.upl.nibss.hcmlib.model.User;

/**
 * Created by toyin.oladele on 16/10/2017.
 */
public interface IUserCache {

    User findUser(String username);

    void saveUser(String username, User user);

    void deleteUser(String username);

}
