package com.upl.nibss.hcm.service.impl;

import com.upl.nibss.hcm.service.interfaces.ILogoutService;
import com.upl.nibss.hcm.dto.logout.LogoutResponse;
import com.upl.nibss.hcmlib.cache.IUserTokenCache;
import com.upl.nibss.hcmlib.config.JWTRedisToken;
import com.upl.nibss.hcmlib.dto.UserDetail;
import com.upl.nibss.hcmlib.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by toyin.oladele on 12/10/2017.
 */
@Service
public class LogoutServiceImpl implements ILogoutService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JWTRedisToken jwtRedisToken;

    @Autowired
    private UserRepo userRepo;

    /**
     * confirm session status (if expired or not)
     *
     * @param userId
     * @return
     */
    @Override
    public boolean isSessionExpired(Long userId) {
        return false;
    }

    /**
     * expire session
     *
     * @param userId
     */
    @Override
    public void expireSession(Long userId) {

    }

    /**
     * log user out.
     *
     * @param userId
     * @return
     */
    @Override
    public String logoutUserId(Long userId) {
        try {
            userRepo.logOutUser(userId);
            return userRepo.findUsernameById(userId);
        } catch (Exception e) {
            logger.error("Unable to logout user, userId -> {}", userId);
        }
        return null;
    }

    @Override
    public boolean isUserOwnToken(String token, Long userId) {

        UserDetail userDetail = jwtRedisToken.decodeToken(token);
        if (userDetail != null &&  userDetail.getUserId() != null && userDetail.getUserId().equals(userId)) {
            return true;
        }

        return false;
    }
}
