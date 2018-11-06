package com.chy.dao.ds2;

import com.chy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chy on 2017/11/25.
 */
public interface User2Repository extends JpaRepository<User, Long> {

    @Query(value = "select * from t_user where id=:id", nativeQuery = true )
    User diySql(@Param("id") Long id);

    @Query(value = "select id, name, password, status from User where id =:id")
    List<User> diyHql(@Param("id") Long id);
}
