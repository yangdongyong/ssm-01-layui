package com.hy.controller;

import com.hy.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author shy Boy
 * @Date 2021/5/20 - 05 - 20 - 17:28
 * @Description: com.hy.controller
 * @version: 1.0
 */
@Controller
public class MyRedisTest {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    RedisTemplate redisTemplate;


    @RequestMapping("/mycode/{key}/{value}")
    //@ResponseBody不会走视图解析器
    @ResponseBody
    public void test01(@PathVariable String key,@PathVariable String value){
        redisUtils.set(key,value);
//        redisTemplate.opsForValue().set(key,value);
        System.out.println(redisUtils.get(key));
    }
}
