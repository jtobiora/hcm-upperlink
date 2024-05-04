package com.upl.nibss.hcm.service.interfaces;

import com.upl.nibss.hcmlib.enums.AuthMode;
import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.User;

import java.util.List;

/**
 * Created by toyin.oladele on 12/10/2017.
 */
public interface ILoginService {


    User getUser(String username, String password);

    User getUser(Long userId);

    /**
     * confirm the mode of authentication(Active Directory or App Database)
     * @return
     */
    AuthMode confirmAuthenticationMode();

    /**
     * Active Directory authentication
     * @param username
     * @param password
     * @return
     */
    User validActiveDirDetail(String username, String password);

    /**
     * App Database authentication
     * @param username
     * @param password
     * @return
     */
    User validAppDatabaseDetail(String username, String password);

    /**
     * Pull Groups for the User
     * @param userId
     * @return
     */
    List<Group> fetchUserGroups(Long userId);

    /**
     * Pull Tasks
     * @param groupIds
     * @return
     */
    List<String> fetchGroupTasks(List<Long> groupIds);

    List<String> fetchGroupModules(List<Long> groupIds);

    void updateUserLoginStatus(User user, boolean isLoggedIn);

}
