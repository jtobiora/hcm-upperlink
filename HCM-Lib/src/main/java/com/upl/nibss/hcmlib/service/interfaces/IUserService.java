package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 12/11/2017.
 */
public interface IUserService {

    User generateUser(User user, Employee employee, Set<Group> groups) throws Exception;

    String generatePassword() throws Exception;

    String generateUsername(String email) throws Exception;

    User save(User user) throws Exception;

    List<User> getAll() throws Exception;

    User get(Long id) throws Exception;

    User getActivatedUserById(Long id) throws Exception;

    User getActivatedUserByUsername(String username) throws Exception;

    User getActivatedUserByEmail(String email) throws Exception;

    Boolean toggle(Long id) throws Exception;

    boolean toggleUserAndEmployee(Long employeeId) throws Exception;

    User getUserByEmployee(Long employeeId) throws Exception;

    List<Group> getGroupByEmployeeId(Long employeeId) throws Exception;

}
