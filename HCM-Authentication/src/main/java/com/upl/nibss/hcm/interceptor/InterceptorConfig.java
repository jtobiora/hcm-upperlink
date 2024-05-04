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


        //update the sessions expire
        iSessionManager.updateTimeout(userDetail.getSessionId());

        return true;
    }
}
