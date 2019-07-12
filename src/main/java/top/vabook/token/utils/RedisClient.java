package top.vabook.token.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: vabook
 * @Date: 2019/7/11 16:05
 * 进行数据操作
 */

@Component
public class RedisClient {


    public static final long TOKEN_EXPIRES_SECOND = 1800;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * key 使用 a:b:id的形式,会看到分层文件夹的展示形式
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        boolean result = false;
        redisTemplate.opsForValue().set(key,value);
        result = true;
        return result;
    }

    // 设置过期时间
    public boolean set(String key,String value,long time){
        boolean result = false;
        redisTemplate.opsForValue().set(key,value);
        result = true;
        System.out.println("=========================保存到redis" + result);
        return result;
    }

    // 取值
    public String get(String key){
        String result = null;
        result = redisTemplate.opsForValue().get(key);
        return result;
    }

    //设置key的过期时间
    public boolean expire(String key, long time){
        boolean result = false;

        if (time > 0 ){
            redisTemplate.expire(key,time, TimeUnit.SECONDS);
            result = true;
        }
        return  result;
    }

    public boolean remove(String key){

        boolean result = false;

        if (key == null)
            return false;
        redisTemplate.delete(key);
        result = true;
        return  result;
    }

}
