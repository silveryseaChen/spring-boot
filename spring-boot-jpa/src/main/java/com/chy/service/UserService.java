package com.chy.service;

import com.chy.model.User;
import com.chy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chy on 2017/11/25.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    public User testTransaction() throws  Exception{

        int rand = new Random().nextInt(1000);

        User user = new User();
        user.setId(1L);
        user.setName("name"+rand);
        user.setPassword("password"+rand);
        user = userRepository.save(user);

        //测试事务
//        if(true)
//            throw new Exception("NEW EXCEPTION..........");
        user.setStatus(1);
        user = userRepository.save(user);

        return user;
    }

    public List<User> diySql(){
        User user = new User();
        user.setId(1L);
        return userRepository.diyHql(1L);
    }

    public List<User> all() {

        return  userRepository.findAll();

    }
}
