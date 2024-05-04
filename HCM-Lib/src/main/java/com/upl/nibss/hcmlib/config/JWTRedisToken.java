package com.upl.nibss.hcmlib.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.upl.nibss.hcmlib.cache.ISessionManager;
import com.upl.nibss.hcmlib.cache.UserTokenCacheService;
import com.upl.nibss.hcmlib.dto.UserDetail;
import com.upl.nibss.hcmlib.enums.SecurityConstants;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by toyin.oladele on 15/10/2017.
 */
@Service
public class JWTRedisToken {

    @Autowired
    private UserTokenCacheService userTokenCacheService;

    @Autowired
    private ISessionManager iSessionManager;

    private final static Logger logger = LoggerFactory.getLogger(UserTokenCacheService.class);

    public String generateAndSaveTokenInRedis(UserDetail userDetail){

        //generate token
        String token = Jwts.builder()
                .setPayload(new Gson().toJson(userDetail))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getValue())
                .compact();

        //save in redis
        if (userTokenCacheService.saveUserToken(SecurityConstants.TOKEN_PREFIX.getValue() +" "+ token, userDetail.getSessionId())){
//            logger.info("User token saved : {}", token);
        }

        return SecurityConstants.TOKEN_PREFIX.getValue()+" "+ token;
    }

    public String generateToken(Object object){

        //generate token
        String token = Jwts.builder()
                .setPayload(new Gson().toJson(object))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getValue())
                .compact();

        return SecurityConstants.TOKEN_PREFIX.getValue()+" "+ token;
    }


    public boolean isValidUserToken(String userToken, String sessionId){

        Object token = userTokenCacheService.findUserToken(userToken, sessionId);
        return token != null;
    }

    public boolean isValidUserSession(String sessionId){
        return iSessionManager.isValidateSession(sessionId);
    }

    /**
     * To decypt token so that we can use this to know which task the user show be able perform
     * @param token
     * @return
     */
    public UserDetail decodeToken(String token){

        if (token != null) {

            // parse the token.
            Claims userDetailString = null;
            try {
                userDetailString = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET.getValue())
                        .parseClaimsJws(token.trim()
                                .replace(" ","")
                                .replace(SecurityConstants.TOKEN_PREFIX.getValue(), ""))
                        .getBody();
            } catch (ExpiredJwtException e) {
                logger.error("Unable to parse token", e);
                return null;
            } catch (UnsupportedJwtException e) {
                logger.error("Unable to parse token", e);
                return null;
            } catch (MalformedJwtException e) {
                logger.error("Unable to parse token", e);
                return null;
            } catch (SignatureException e) {
                logger.error("Unable to parse token", e);
                return null;
            } catch (IllegalArgumentException e) {
                logger.error("Unable to parse token", e);
                return null;
            } catch (Exception e){
                logger.error("Unable to parse token", e);
                return null;
            }

            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(Long.valueOf(String.valueOf(userDetailString.get("userId"))));
            userDetail.setName(userDetailString.get("name", String.class));
            userDetail.setSessionId(userDetailString.get("sessionId", String.class));
            userDetail.setGeneratedPassword(userDetailString.get("generatedPassword", Boolean.class));
            userDetail.setGroups((List<String>) userDetailString.get("groups"));
            userDetail.setModules((List<String>) userDetailString.get("modules"));
            userDetail.setEmployee((Map<String, Object>) userDetailString.get("employee"));
            userDetail.setEmployeeId(Long.valueOf(String.valueOf(userDetailString.get("employeeId"))));

            if (userDetail.getUserId() != null) {
                return userDetail;
            }

            return null;
        }

        return null;
    }

}
