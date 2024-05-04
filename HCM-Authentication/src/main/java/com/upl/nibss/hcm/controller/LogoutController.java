package com.upl.nibss.hcm.controller;

import com.upl.nibss.hcm.dto.logout.LogoutResponse;
import com.upl.nibss.hcm.enums.AuthError;
import com.upl.nibss.hcm.service.interfaces.ILogoutService;
import com.upl.nibss.hcmlib.cache.IUserTokenCache;
import com.upl.nibss.hcmlib.config.JWTRedisToken;
import com.upl.nibss.hcmlib.dto.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by toyin.oladele on 12/10/2017.
 */
@RestController
public class LogoutController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ILogoutService iLogoutService;

    @Autowired
    private IUserTokenCache iUserTokenCache;

    @Autowired
    private JWTRedisToken jwtRedisToken;

    @GetMapping( value = "/user/{id}/logout", produces = "application/json")
    public ResponseEntity<LogoutResponse> logout(@PathVariable Long id, @RequestHeader("Authorization") String authorization, HttpSession httpSession){

        LogoutResponse response = new LogoutResponse();

        if (id == null){
            response.setStatus(false);
            response.setMessage(AuthError.INVALID_REQUEST.getValue());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (authorization == null){
            logger.error("authorization not found");
            response.setStatus(false);
            response.setMessage(AuthError.MISSING_TOKEN.getValue());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        UserDetail userDetail = jwtRedisToken.decodeToken(authorization);

        Object userToken = iUserTokenCache.findUserToken(authorization, userDetail.getSessionId());
        if (userToken == null){
            response.setStatus(false);
            response.setMessage(AuthError.EXPIRED_TOKEN.getValue());
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

//        if (!iLogoutService.isUserOwnToken(userToken,id)){
//            response.setStatus(false);
//            response.setMessage(AuthError.WRONG_USER.getValue());
//            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
//        }

        //logout the user by remove the session from redis
        iUserTokenCache.deleteUserToken(authorization,  userDetail.getSessionId());

        //set the isloggedIn to false
        iLogoutService.logoutUserId(id);

        httpSession.invalidate();

        response.setStatus(true);
        response.setMessage("Successfully logged out");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
