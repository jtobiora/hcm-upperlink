package com.upl.nibss.hcm.controller;

import com.upl.nibss.hcm.dto.login.LoginRequest;
import com.upl.nibss.hcm.dto.login.LoginResponse;
import com.upl.nibss.hcm.enums.AuthError;
import com.upl.nibss.hcm.service.interfaces.ILoginService;
import com.upl.nibss.hcmlib.config.JWTRedisToken;
import com.upl.nibss.hcmlib.dto.UserDetail;
import com.upl.nibss.hcmlib.embeddables.DepartmentEmbeddable;
import com.upl.nibss.hcmlib.embeddables.NameType;
import com.upl.nibss.hcmlib.embeddables.Person;
import com.upl.nibss.hcmlib.enums.Constants;
import com.upl.nibss.hcmlib.enums.UserDetailEmployeeContants;
import com.upl.nibss.hcmlib.model.GradeLevel;
import com.upl.nibss.hcmlib.model.Group;
import com.upl.nibss.hcmlib.model.User;
import com.upl.nibss.hcmlib.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by toyin.oladele on 12/10/2017.
 */
@RestController
public class LoginController implements UserDetailEmployeeContants {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ILoginService iLoginService;

    @Autowired
    private JWTRedisToken jwtRedisToken;

    @Autowired
    private IUserService iUserService;

    @PostMapping( value = "/login", produces = "application/json")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession httpSession) throws Exception{

        LoginResponse response = new LoginResponse();

        if (loginRequest == null){
            response.setMessage(AuthError.INVALID_REQUEST.getValue());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

//        logger.info("The login request is {}", loginRequest);

        //confirm the user exist on our system and it is not deleted.
        User user = iLoginService.getUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (user == null || user.getId() == null){
            //user does not exist.
            logger.info("The user is Invalid");
            response.setMessage(AuthError.INVALID_USER.getValue());
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        if (!user.isActivated()){
            response.setMessage(AuthError.DISABLED_USER.getValue());
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.isDeleted()){
            response.setStatus(false);
            response.setMessage(AuthError.DELETED_USER.getValue());
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.isLoggedIn()){
            response.setStatus(false);
            response.setMessage(AuthError.ALREADY_LOGGED_IN.getValue());
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        //update the logged in status
//        iLoginService.updateUserLoginStatus(user, true);

        //fetch the user's groups and tasks
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(user.getId());
        userDetail.setName(user.getUsername());

        List<Group> groups = iLoginService.fetchUserGroups(user.getId());
        List<String> groupsNames = new ArrayList<>();
        List<Long> groupsIds = new ArrayList<>();

        groups.stream().forEach(g->{
            groupsNames.add(g.getName());
            groupsIds.add(g.getId());
        });

        userDetail.setGroups(groupsNames);
        userDetail.setModules(iLoginService.fetchGroupModules(groupsIds));
        userDetail.setSessionId(httpSession.getId());
        userDetail.setGeneratedPassword(user.isGeneratedPassword());
        userDetail.getEmployee().put(NAME, user.getEmployee() != null ? user.getEmployee().getEmpName(): new NameType());
        userDetail.getEmployee().put(EMAIL, user.getEmployee() != null ? user.getEmployee().getEmail(): null);
        userDetail.getEmployee().put(NUMBER, user.getEmployee() != null ? user.getEmployee().getEmployeeNo(): Constants.EMPTY);
        userDetail.getEmployee().put(CONFIRMATION_STATUS, user.getEmployee() != null ? user.getEmployee().getConfirmationStatus(): null);
        userDetail.getEmployee().put(RESUMPTION_DATE, user.getEmployee() != null ? user.getEmployee().getActualResumptionDate(): null);
        userDetail.getEmployee().put(DEPARTMENT, user.getEmployee() != null ? user.getEmployee().getDepartment() : new DepartmentEmbeddable());
        userDetail.getEmployee().put(JOB_ROLE_ID, (user.getEmployee() != null && user.getEmployee().getJobRole() != null) ? user.getEmployee().getJobRole().getJobRoleId() : null );
        userDetail.getEmployee().put(PERSON_DETAIL, user.getEmployee() != null ? user.getEmployee().getEmployeePersonDetail() : new Person());
        userDetail.getEmployee().put(EMPLOYEE_TYPE, user.getEmployee() != null ? user.getEmployee().getEmployeeType() : null);
        userDetail.getEmployee().put(GRADE_LEVEL, user.getEmployee() != null ? user.getEmployee().getGradeLevel() : new GradeLevel());
        userDetail.getEmployee().put(LEAVE_STATUS, user.getEmployee() != null ? user.getEmployee().getLeaveStatus() : null);
        userDetail.getEmployee().put(LOAN_ELIGIBLE, user.getEmployee() != null ? user.getEmployee().getLoanEligibility() : null);
        userDetail.getEmployee().put(IS_SUPERVISOR,user.getEmployee() != null ? user.getEmployee().isSupervisor() : false);
        userDetail.getEmployee().put(IS_HCM,user.getEmployee() != null ?user.getEmployee().isHcm() : false);
        userDetail.setEmployeeId(user.getEmployee() == null ? null : user.getEmployee().getId());
        response.setStatus(true);

        //create a token using jwt and redis
        String token = jwtRedisToken.generateAndSaveTokenInRedis(userDetail);

        //update the sessionId
        user.setCurrentSessionId(userDetail.getSessionId());
        iUserService.save(user);

        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

