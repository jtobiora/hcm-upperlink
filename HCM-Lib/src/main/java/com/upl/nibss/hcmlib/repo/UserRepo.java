PACKAGE com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by toyin.oladele on 10/10/2017.
 */
@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.activated = true and u.deleted = false ")
    List<User> findAllActivated();

    @Query("select u from User u where u.id = :id and u.deleted = false")
    User findOne(@Param("id") Long id);

    @Query("select u from User u where u.id = :id and u.activated = true and u.deleted = false")
    User findOneAndActivated(@Param("id") Long id);

    @Query("select u from User u where u.username = :username and u.password = :password and u.activated = true and u.deleted = false")
    User findAllByUsernameAAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("select u from User u where u.username = :username and u.deleted = false")
    User findUserByUsername(@Param("username") String username);

    @Query("select u from User u where u.username = :username and u.activated = true and u.deleted = false")
    User findUserByUsernameAndActivated(@Param("username") String username);

    @Query("select u from User u where u.employee.email = :email and u.activated = true and u.deleted = false")
    User findUserByEmailAndActivated(@Param("email") String email);

    @Modifying
    @Query("update User u set u.loggedIn = false , u.updatedAt = current_date where u.id = :userId")
    void logOutUser(@Param("userId") Long userId);

    @Query("select u.username from User u where u.id = :userId ")
    String findUsernameById(@Param("userId") Long userId);

    @Query("select u.groups from User u where u.id = :id")
    Set<Group> findUserGroupsById(@Param("id") Long id);

    @Query("select u from User u where u.employee.id = :employeeId")
    User findUserByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("select u.groups from User u where u.employee.id = :employeeId")
    Set<Group> findGroupByEmployeeId(@Param("employeeId") Long employeeId);

//    @Modifying
//    @Query("update User u set u.activated = (case u.activated when true then false else true end) , u.employee.activated = (case u.activated when true then false else true end) where u.employee.id = :id")
//    int toggleUserAndEmployeeStatus(@Param("id") Long id);

}
