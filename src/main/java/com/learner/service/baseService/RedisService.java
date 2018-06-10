package com.learner.service.baseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liufangliang on 2018/5/31.
 */
@Service
public class RedisService {

    @Resource
    private RedisTemplate redisTemplate;


    public void add(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    public static void main(String[] args) {
        RedisTemplate redis = new RedisTemplate();
        redis.opsForValue().set("test","test");
    }
}
