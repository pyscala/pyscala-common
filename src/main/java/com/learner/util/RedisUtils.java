package com.learner.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by liufangliang on 2018/6/2.
 */
@Slf4j
public class RedisUtils {

    public static final String LOCK_CONTEXT="lock_success";

    /** 分布式update 保证一致性
     *
     * @param redisTemplate
     * @param key
     * @param value
     * @return  是否修改成功
     */
    public static boolean RedisSetNX(final RedisTemplate redisTemplate,final String key,final String value){
        Boolean result=false;
        if(StringUtils.isEmpty(key)){
            log.info(String.format("redis key is null or string lenth for zore"));
            return false;
        }
        try {
            result=(Boolean)redisTemplate.execute(new RedisCallback() {
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    return  redisConnection.setNX(redisTemplate.getStringSerializer().serialize(key),redisTemplate.getStringSerializer().serialize(value));
                }
            });
        } catch (Exception e) {
            log.error(String.format("redis setNX key:value ======{}:{} exception ! {}",key,value,e));
        }
        return result;
    }

    /**
     *
     * @param redisTemplate
     * @param lockName 锁的唯一id
     * @param lockTimeout  锁的超时时间  second
     * @param acquireTimeout  尝试获取锁的时间 decond
     * @param retryDuartion  尝试重试时间间隔  millis
     * @return
     */
    public static boolean tryLock(final RedisTemplate redisTemplate,final String lockName,final int lockTimeout,final int acquireTimeout,final long retryDuartion){

        Date current=new Date();
        Calendar cal =Calendar.getInstance();
        cal.setTime(current);
        cal.add(Calendar.SECOND,acquireTimeout);
        log.info(String.format("try to arquire  the lock ,lockname={},locktimeout={},acquireTimeout={},retryDuartion={}",lockName,lockTimeout,acquireTimeout,retryDuartion));
        while (true){
            Boolean lockStatus=false;
            try {
                lockStatus=(Boolean)redisTemplate.execute(new RedisCallback() {
                    public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                       return redisConnection.setNX(redisTemplate.getStringSerializer().serialize(lockName),redisTemplate.getStringSerializer().serialize(LOCK_CONTEXT));
                    }
                });
            } catch (Exception e) {
                log.error(String.format("{} get lock faild {} ",lockName,e));
            }
            if(lockStatus){//成功获得锁，设置过期时间
                Boolean lockTimeStatus=false;
                try {
                    lockTimeStatus=(Boolean)redisTemplate.execute(new RedisCallback() {
                        public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                            return redisConnection.expire(redisTemplate.getStringSerializer().serialize(lockName),lockTimeout);
                        }
                    });
                } catch (Exception e) {
                    log.error(String.format("{} set locktimeout faild {}",lockName,e));
                }
                return true;
            }else{ //获取锁失败，根据设置的时间决定是返回失败还是重试。
                if(acquireTimeout<=0){
                    return false;
                }else{
                    try {
                        log.info(String.format("{} retry get lock ",lockName));
                        Thread.sleep(retryDuartion);
                    } catch (InterruptedException e) {
                        log.error(String.format("{} retry sleep {} millis faild {} ",lockName,retryDuartion,e));
                    }
                }
            }

            if(new Date().getTime()>cal.getTimeInMillis()){
                break;
            }
        }

        return false;

    }

    public static void unLock(final RedisTemplate redisTemplate,final String lockName){
        if (StringUtils.isEmpty(lockName)) {
            return;
        }
        try {
            redisTemplate.execute(new RedisCallback() {
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                   return redisConnection.del(redisTemplate.getStringSerializer().serialize(lockName));
                }
            });
        } catch (Exception e) {
            log.error(String.format("{} unlock faild {}",lockName,e));
        }
    }



    //检查锁状态，锁住返回true,否则false
    public static Boolean checkWhetherLockExists(final RedisTemplate jedisTemplate, final String lockName) {
        if (StringUtils.isEmpty(lockName)) {
            return false;
        }
        boolean lockExists = (Boolean)jedisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bytes = connection.get(jedisTemplate.getStringSerializer().serialize(lockName));
                if(null != bytes){
                    return true;
                } else {
                    return false;
                }
            }
        });

        return lockExists;
    }

}
