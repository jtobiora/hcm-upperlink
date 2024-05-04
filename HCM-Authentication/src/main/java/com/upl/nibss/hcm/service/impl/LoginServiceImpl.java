package com.upl.nibss.hcm.service.impl;

import com.upl.nibss.hcm.service.interfaces.ILoginService;
import com.upl.nibss.hcmlib.enums.AuthMode;
import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.Task;
import com.upl.nibss.hcmlib.model.User;
import com.upl.nibss.hcmlib.repo.GroupRepo;
import com.upl.nibss.hcmlib.repo.SettingRepo;
import com.upl.nibss.hcmlib.repo.TaskRepo;
import com.upl.nibss.hcmlib.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 12/10/2017.
 */
@Service
public class LoginServiceImpl implements ILoginService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SettingRepo settingRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User getUser(String username, String password) {

        AuthMode authenticationMode = confirmAuthenticationMode();
        User user;
        switch (authenticationMode){
            case ACTIVE_DIRECTORY:
                user = validActiveDirDetail(username, password);
                break;

            case APP_DATABASE:
                user = validAppDatabaseDetail(username, password);
                break;

            default:
                user = validAppDatabaseDetail(username, password);
        }

        return user;
    }

    @Override
    public User getUser(Long userId) {

        try {
            return userRepo.findOne(userId);
        } catch (Exception e) {
            logger.error("Unable to find the user with user id -> {}", userId, e);
        }

        return new User();
    }

    /**
     * confirm the mode of authentication(Active Directory or App Database)
     *
     * @return
     */
    @Override
    public AuthMode confirmAuthenticationMode() {

        String valueByName = settingRepo.findValueByName(AuthMode.AUTHENTICATION_MODE.toString());
        if (valueByName == null || valueByName.isEmpty()){
            return AuthMode.APP_DATABASE;
        }

        return AuthMode.valueOf(valueByName);
    }

    /**
     * Active Directory authentication
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User validActiveDirDetail(String username, String password) {
        return new User();
    }

    /**
     * App Database authentication
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User validAppDatabaseDetail(String username, String password) {

        User user = null;
        try {
//            user = userRepo.findAllByUsernameAAndPassword(username, passwordEncoder.encode(password));
            user = userRepo.findUserByUsernameAndActivated(username);
            if(user != null){
                if (!passwordEncoder.matches(password,user.getPassword())){
                    user = null;
                }
            }
        } catch (Exception e) {
            logger.error("Unable to fetch User detail By Username : {}, Password : {}", username, password, e);
        }

        if (user != null){
            return user;
        }

        return new User();
    }

    /**
     * Pull Groups for the User
     *
     * @param userId
     * @return
     */
    @Override
    public List<Group> fetchUserGroups(Long userId) {

        List<Group> groups =  new ArrayList<>(userRepo.findUserGroupsById(userId));
        if(groups == null){
            groups = new ArrayList<>();
        }

        return groups;
    }

    /**
     * Pull Tasks
     *
     * @param groupIds
     * @return
     */
    @Override
    public List<String> fetchGroupTasks(List<Long> groupIds){

            if (groupIds != null){
                List<String> taskNames = new ArrayList<>();
                Set<Task> tasks = getGroupTasks(groupIds);
                tasks.stream().forEach(t -> taskNames.add(t.getName()));
                return taskNames;
            }else {
                logger.error("groupIds is NULL");
                return new ArrayList<>();
            }
    }

    @Override
    public List<String> fetchGroupModules(List<Long> groupIds) {

        if (groupIds != null){

            Set<String> moduleNames = new HashSet<>();
            Set<Task> tasks = getGroupTasks(groupIds);
            tasks.stream().forEach(t -> moduleNames.add(t.getModule().getName()));
            return new ArrayList<>(moduleNames);

        }else {
            logger.error("groupIds is NULL");
            return new ArrayList<>();
        }

    }

    @Override
    public void updateUserLoginStatus(User user, boolean isLoggedIn) {

        user.setLoggedIn(isLoggedIn);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        if (isLoggedIn){
            user.setLastLogIn(new Timestamp(System.currentTimeMillis()));
        }
        try {
            userRepo.save(user);
        } catch (Exception e) {
            logger.error("Unable to Update the user login status", e);
        }
    }

    private Set<Task> getGroupTasks(List<Long> groupIds){

        if (groupIds == null || groupIds.size() < 1){
            logger.error("Group Id is empty or null");
            return new HashSet<>();
        }

        Set<Task> tasks = groupRepo.findGroupTasksByIds(groupIds);
        return tasks;
    }
}
