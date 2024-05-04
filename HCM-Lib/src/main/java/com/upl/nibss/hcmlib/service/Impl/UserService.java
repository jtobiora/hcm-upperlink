package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.cache.ISessionManager;
import com.upl.nibss.hcmlib.cache.IUserTokenCache;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.User;
import com.upl.nibss.hcmlib.repo.UserRepo;
import com.upl.nibss.hcmlib.service.interfaces.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 12/11/2017.
 */
@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Value("${characters}")
    private String characters;

    @Value("${defaultPasswordLength}")
    private int defaultPasswordLength;

    @Autowired
    private ISessionManager iSessionManager;

    @Override
    public User generateUser(User user, Employee employee, Set<Group> groups) throws Exception {

        user.setActivated(true);
        user.setEmployee(employee);
        user.setGroups(groups);
        user.setPassword(generatePassword());
        user.setUsername(generateUsername(employee.getEmail()));
        user.setGeneratedPassword(true);

        return user;
    }

    @Override
    public String generatePassword() throws Exception {
        return RandomStringUtils.random(defaultPasswordLength, characters);
    }

    @Override
    public String generateUsername(String email) throws Exception {
        return email.split("@")[0].replace(" ","");
    }

    @Override
    public User save(User user) throws Exception {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepo.findAll();
    }

    @Override
    public User get(Long id) throws Exception {
        return userRepo.findOne(id);
    }

    @Override
    public User getActivatedUserById(Long id) throws Exception {
        return userRepo.findOneAndActivated(id);
    }

    @Override
    public User getActivatedUserByUsername(String username) throws Exception {
        return userRepo.findUserByUsernameAndActivated(username);
    }

    @Override
    public User getActivatedUserByEmail(String email) throws Exception {
        return userRepo.findUserByEmailAndActivated(email);
    }

    @Override
    public Boolean toggle(Long id) throws Exception {

        User user = userRepo.findOne(id);
        user.setActivated(!user.isActivated());
        iSessionManager.deleteSession(user.getCurrentSessionId());
        user = userRepo.save(user);

        if (user != null){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public boolean toggleUserAndEmployee(Long employeeId) throws Exception {
//        int affectedRows = userRepo.toggleUserAndEmployeeStatus(employeeId);
//        System.out.println(affectedRows);
//        if (affectedRows > 0){
//            return true;
//        }
        return false;
    }

    @Override
    public User getUserByEmployee(Long employeeId) throws Exception {
        return userRepo.findUserByEmployeeId(employeeId);
    }

    @Override
    public List<Group> getGroupByEmployeeId(Long employeeId) throws Exception {
        Set<Group> groups = userRepo.findGroupByEmployeeId(employeeId);
        List<Group> activatedGroups = new ArrayList<>();
        groups.forEach(group -> {if (group.isActivated() && !(group.isDeleted())) { activatedGroups.add(group); }});
        return activatedGroups;
    }
}
