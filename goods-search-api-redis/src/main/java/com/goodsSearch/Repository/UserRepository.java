package com.goodsSearch.Repository;

import com.goodsSearch.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by hanhansongjiang on 17/5/12.
 */
public interface UserRepository extends CrudRepository<User,String> {

    User findByName(String name);




    @Modifying
    @Query("update User u set u.name=?1 where u.id=?2")
    int update(String name, String id);
}
