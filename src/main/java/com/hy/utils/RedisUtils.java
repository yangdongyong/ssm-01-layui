package com.hy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @Author shy Boy
 * @Date 2021/5/20 - 05 - 20 - 17:06
 * @Description: com.hy.utils
 * @version: 1.0
 */
@Component
public class RedisUtils {
    @Autowired
    RedisTemplate redisTemplate;

    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }


/*
    设置String 类型Key-value值
 */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }


}
