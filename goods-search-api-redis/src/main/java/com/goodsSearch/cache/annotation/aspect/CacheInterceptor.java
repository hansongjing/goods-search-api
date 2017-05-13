package com.goodsSearch.cache.annotation.aspect;

import com.goodsSearch.RedisUtil;
import com.goodsSearch.bean.User;
import com.goodsSearch.cache.annotation.RedisEvict;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * Created by hanhansongjiang on 17/5/12.
 */
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

    @Pointcut("execution(* com.goodsSearch.service.UserService.*(..))" +
            " && @annotation(com.goodsSearch.cache.annotation.RedisEvict)")
    public void pointcutLock() {}


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



    @Around("pointcutLock()")
    public Object doBefore(ProceedingJoinPoint jp) throws Throwable {

        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        // 得到被代理的方法上的注解
        Class modelType = method.getAnnotation(RedisEvict.class).type();

        // 清除对应缓存
        redisUtil.deleteUser(modelType.getName());
        return jp.proceed(jp.getArgs());


    }

}
