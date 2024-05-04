package com.upl.nibss.hcmlib.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * Created by toyin.oladele on 15/10/2017.
 */
@Repository
public class CustomDataCacheService {

    private static final String KEY = "customData";
    private static final Logger logger = LoggerFactory.getLogger(CustomDataCacheService.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private HashOperations hashOperations;

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public Object get(String key) {
        return hashOperations.get(KEY, key);
    }

    public boolean save(String key, String value) {

        if (value == null){
            logger.error("value is NULL...unable to save... returning false");
            return false;
        }
        hashOperations.put(KEY, key , value);
        return true;
    }

    public void delete(String key) {
        hashOperations.delete(KEY, key);
    }

}
