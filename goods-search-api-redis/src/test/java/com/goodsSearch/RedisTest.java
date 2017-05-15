package com.goodsSearch;

import com.goodsSearch.bean.User;
import com.goodsSearch.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by hanhansongjiang on 17/5/12.
 */
public class RedisTest extends BaseTest {


    @Autowired
    private RedisUtil<User> redisUtil;

    @Autowired
    private UserService userService;

    @Test
    public void testSetValue() {

        redisUtil.setValue("han",new User("han","age","china",10));


    }

    @Test
    public void testGetValue() {

       User user= redisUtil.getValue("han");

        Assert.assertEquals("han",user.getId());


    }

    @Test
    public void updateUserName(){

        userService.updateNameById("hello","10");
    }


    @Test
    public void select(){

        for(int i=0;i<10;i++) {
            userService.findUserById("10");
        }
    }




}
