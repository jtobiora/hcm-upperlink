package com.upl.nibss.hcmlib.config;

import com.google.gson.Gson;
import com.upl.nibss.hcmlib.cache.CustomDataCacheService;
import com.upl.nibss.hcmlib.dto.UserDetail;
import com.upl.nibss.hcmlib.model.Employee;
import com.upl.nibss.hcmlib.model.User;
import com.upl.nibss.hcmlib.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by stanlee on 11/03/2018.
 */

public class AuditorAwareImpl implements AuditorAware<Employee> {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTRedisToken jwtRedisToken;

    @Autowired
    private CustomDataCacheService customDataCacheService;

    @Override
    public Employee getCurrentAuditor() {
        UserDetail userDetail = getUsernameAndEmployeeDetails();
        return new Employee(userDetail.getEmployeeId());
        // your custom logic
    }

    private UserDetail getUsernameAndEmployeeDetails(){
        UserDetail userDetail = null;

        try {
            //get the user's information
            userDetail = jwtRedisToken.decodeToken(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("Authorization"));
            if (userDetail != null) {
                return userDetail;
            }
        } catch (IllegalStateException e) {
//            logger.error("Exceptions", e);
        }

        return getSystemUser();
    }

    private UserDetail getSystemUser(){

        String userObject = String.valueOf(customDataCacheService.get("user"));

        if (userObject.equalsIgnoreCase("null") || userObject.equalsIgnoreCase(null)){

            //fetch from DB
            User user = userRepo.findUserByUsername("user");

            if (user != null && user.getEmployee() != null){
                UserDetail userDetail = new UserDetail(user.getUsername(), user.getEmployee().getId());
                userObject = new Gson().toJson(userDetail);
                customDataCacheService.save("user", userObject);
            }else {
                System.out.println("User is null or the user.Employee is NULL");
            }

        }

        return new Gson().fromJson(userObject, UserDetail.class);
    }

}