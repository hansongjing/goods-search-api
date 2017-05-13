package com.goodsSearch.service;

import com.goodsSearch.Repository.UserRepository;
import com.goodsSearch.bean.User;
import com.goodsSearch.cache.annotation.RedisCache;
import com.goodsSearch.cache.annotation.RedisEvict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hanhansongjiang on 17/5/12.
 */
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    //更新用户名字
    @Transactional
    @RedisEvict(type = User.class)
     public int updateNameById(String userName,String id){

         return userRepository.update(userName,id);

     }


    @RedisCache(type=User.class)
    public  User  findUserById(String id){
        return userRepository.findOne(id);
    }





}
