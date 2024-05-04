package com.upl.nibss.hcm.service.interfaces;

/**
 * Created by toyin.oladele on 12/10/2017.
 */
public interface ILogoutService {

    /**
     * confirm session status (if expired or not)
     * @param userId
     * @return
     */
    boolean isSessionExpired(Long userId);

    /**
     * expire session
     * @param userId
     */
    void expireSession(Long userId);

    /**
     * log user out.
     * @param userId
     * @return
     */
    String logoutUserId(Long userId);

    boolean isUserOwnToken(String token, Long userId);

}
