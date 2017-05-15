package com.goodsSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hanhansongjiang on 17/5/12.
 */

@Service
public class RedisUtil<T> {


    //占用锁的最大时间 单位 秒
    private static final int LOCK_HOLDING_MAX_SECONDS = 60;

    // 线程休息时间 单位 毫秒
    private static final int LOCK_RETRY_INTERVAL = 50;

    /**
     * 锁定库存的脚本，是保证setnx和 expire操作的原子
     */
    private static final String LOCK_LUA_SCRIPT = "local flag = redis.call('setnx',KEYS[1],0); "
            + "if(flag == 1) then local maxtime = tonumber(ARGV[1]); redis.call('expire',KEYS[1],maxtime) end ; "
            + "return tonumber(flag) == 1;";

    @Autowired
    private RedisTemplate<String ,Object > redisTemplate;


    public void setValue(String key, T user) {

        redisTemplate.opsForValue().set(key, user);

    }

    public T getValue(String key){
      return (T) redisTemplate.opsForValue().get(key);
    }


    public void deleteUser(String key){
        redisTemplate.delete(key);
    }



    public boolean isExist(String key){ return redisTemplate.opsForValue().get(key)==null;}

}



