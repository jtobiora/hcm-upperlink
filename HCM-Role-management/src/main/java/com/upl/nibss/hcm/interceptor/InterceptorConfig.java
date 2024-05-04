package com.upl.nibss.hcm.interceptor;

import com.upl.nibss.hcm.enums.Errors;
import com.upl.nibss.hcmlib.cache.ISessionManager;
import com.upl.nibss.hcmlib.cache.IUserTokenCache;
import com.upl.nibss.hcmlib.config.JWTRedisToken;
import com.upl.nibss.hcmlib.dto.UserDetail;
import com.upl.nibss.hcmlib.enums.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by toyin.oladele on 19/10/2017.
 */
@Component
public class InterceptorConfig extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

    @Autowired
    private JWTRedisToken jwtRedisToken;

    @Autowired
    private IUserTokenCache userTokenCache;

    @Autowired
    private ISessionManager iSessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("OPTIONS".equals(request.getMethod())){
            return true;
        }

        if (request.getServletPath().matches("/group/employees/\\d+") || request.getServletPath().matches("/group/enabled")){
            return true;
        }

        UserDetail userDetail = jwtRedisToken.decodeToken(request.getHeader(SecurityConstants.HEADER_STRING.getValue()));
        if (userDetail == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Errors.UNKNOWN_USER.getValue());
            return false;
        }

        if (!jwtRedisToken.isValidUserSession(userDetail.getSessionId())) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Errors.EXPIRED_SESSION.getValue());
            return false;
        }

        if (!jwtRedisToken.isValidUserToken(request.getHeader(SecurityConstants.HEADER_STRING.getValue()), userDetail.getSessionId())) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Errors.EXPIRED_TOKEN.getValue());
            return false;
        }

        List<String> task = userTokenCache.getTask(userDetail.getSessionId(), request.getHeader(SecurityConstants.HEADER_STRING.getValue()));
        //confirm that the this user have the right visit this url
        if (!isUserAuthorized(task,request)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Errors.NOT_PERMITTED.getValue());
            return false;
        }

        //update the sessions expire
        iSessionManager.updateTimeout(userDetail.getSessionId());

        return true;
    }

    private boolean isUserAuthorized(List<String> tasks, HttpServletRequest request){

        //group
        if (request.getServletPath().matches("/group") && tasks.contains("all group")){
            return true;
        }

        if (request.getServletPath().matches("/group/\\d+") && tasks.contains("view group")){
            return true;
        }

        if (request.getServletPath().matches("/group") && request.getMethod() == "POST" && tasks.contains("create group")){
            return true;
        }

        if (request.getServletPath().matches("/group/\\d+") && request.getMethod() == "PUT" && tasks.contains("update group")){
            return true;
        }

        if (request.getServletPath().matches("/group/\\d+/toggle") && tasks.contains("toggle group")){
            return true;
        }

        //privilege
        if (request.getServletPath().equals("/privilege") && tasks.contains("all privilege")){
            return true;
        }

        if (request.getServletPath().matches("/privilege/\\d+") && tasks.contains("view privilege")){
            return true;
        }

        if (request.getServletPath().matches("/privilege")  && request.getMethod() == "POST" && tasks.contains("create privilege")){
            return true;
        }

        if (request.getServletPath().matches("/privilege/\\d+") && request.getMethod() == "PUT" && tasks.contains("update privilege")){
            return true;
        }

        if (request.getServletPath().matches("/privilege/\\d+/toggle") && tasks.contains("toggle privilege")){
            return true;
        }

        if (request.getServletPath().matches("/privilege/createByGroupName") && tasks.contains("privilege create by group name")){
            return true;
        }

        return false;

    }
}
