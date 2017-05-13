package com.goodsSearch;

import com.goodsSearch.Repository.UserRepository;
import com.goodsSearch.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Created by hanhansongjiang on 17/5/12.
 */
@Rollback(false)
 public class JpaTest extends  BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave(){

        userRepository.save(new User("10","34","china",10));
       User user= userRepository.findOne("20");


        Assert.assertEquals(user.getAddress(),"china");

    }


}
