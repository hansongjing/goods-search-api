package com.goodsSearch.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hanhansongjiang on 17/5/12.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisCache {

    /**
     *缓存处理的类型
     */

    Class type();

    /**
     *缓存的过期时间
     */
    int expire() default 0;


}
