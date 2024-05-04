package com.upl.nibss.hcm.cache;

import com.upl.nibss.hcmlib.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * Created by toyin.oladele on 16/10/2017.
 */
@Repository
public class UserCache implements IUserCache {
    private static final String KEY = "userObject";
    private static final Logger logger = LoggerFactory.getLogger(UserCache.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public User findUser(String username) {
        return (User) hashOperations.get(KEY, username);
    }

    @Override
    public void saveUser(String username, User user) {
        hashOperations.put(KEY, username, user);
    }

    @Override
    public void deleteUser(String username) {
        hashOperations.delete(KEY, username);
    }
}
