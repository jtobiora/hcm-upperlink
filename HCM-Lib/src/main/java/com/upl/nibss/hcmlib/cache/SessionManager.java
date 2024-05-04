package com.upl.nibss.hcmlib.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by toyin.oladele on 23/10/2017.
 */
@Repository
public class SessionManager implements ISessionManager {

    private static final String KEY = "spring:session:mysession:sessions:expires:";
    private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private HashOperations hashOperations;

    @Value("${session-timeout}")
    private Long sessionTimeout;

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void updateTimeout(String sessionId) {
        hashOperations.getOperations().expire(KEY+sessionId,sessionTimeout, TimeUnit.SECONDS);
    }

    @Override
    public boolean isValidateSession(String sessionId) {
        return hashOperations.getOperations().hasKey(KEY+sessionId);
    }

    @Override
    public void deleteSession(String sessionId) {
        hashOperations.getOperations().delete(KEY+sessionId);
    }
}
