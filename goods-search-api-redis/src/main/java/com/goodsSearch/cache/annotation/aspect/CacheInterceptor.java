package com.goodsSearch.cache.annotation.aspect;

import com.goodsSearch.RedisUtil;
import com.goodsSearch.bean.User;
import com.goodsSearch.cache.annotation.RedisCache;
import com.goodsSearch.cache.annotation.RedisEvict;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Stream;


/**
 * Created by hanhansongjiang on 17/5/12.
 */
@Slf4j
@Aspect
@Component
public class CacheInterceptor {



    @Autowired
    private RedisUtil<User> redisUtil;

    /**
     * Service层切点 使用到了我们定义的 RedisCache 作为切点表达式。
     * 而且我们可以看出此表达式基于 annotation。
     * 并且用于内建属性为查询的方法之上
     */

    @Pointcut("@annotation(com.goodsSearch.cache.annotation.RedisCache)&& execution(* *(..))")
    public void redisCacheAspect() {
    }

    /**
     * Service层切点 使用到了我们定义的 RedisEvict 作为切点表达式。
     * 而且我们可以看出此表达式是基于 annotation 的。
     * 并且用于内建属性为非查询的方法之上，用于更新表
     */
    @Pointcut("@annotation(com.goodsSearch.cache.annotation.RedisEvict) && execution(* *(..))")
    public void redisCacheEvict() {
    }


    /**
     * 此注解会清除相应的key,主要用于更新操作
     */

    @Around("redisCacheEvict()")
    public Object doEvict(ProceedingJoinPoint jp) throws Throwable {

        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        // 得到被代理的方法上的注解
        Class modelType = method.getAnnotation(RedisEvict.class).type();

        // 清除对应缓存
        redisUtil.deleteUser(keyGen(method, jp.getArgs()));
        return jp.proceed(jp.getArgs());


    }

    @Around("redisCacheAspect()")
    public Object cache(ProceedingJoinPoint jp) {

        Method method = ((MethodSignature) jp.getSignature()).getMethod();

        Class modelType = method.getAnnotation(RedisCache.class).type();
        String key = keyGen(method, jp.getArgs());

        //1查看redis是否存在这个key

        User user = redisUtil.getValue(key);
        Object ans=null;

        //2.存在的话从缓存返回
        if (user != null) {

            log.info(key+"    exist");
            return user;
        } else {  //不存在,从数据库读取后,存入缓存

            try {
                log.info(key+"   not-exist");

                ans= jp.proceed(jp.getArgs());

                redisUtil.setValue(key, (User) ans);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }


        }


       return ans;
    }


    private String keyGen(Method method, Object args[]) {


        StringJoiner joiner = new StringJoiner("");
        for (Object cs: args) {
            joiner.add(cs.toString());
        }

        return method.getDeclaringClass()
                + method.getName()
                +joiner.toString();


    }


}
