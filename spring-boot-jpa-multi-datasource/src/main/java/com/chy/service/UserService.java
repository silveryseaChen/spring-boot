package com.chy.service;

import com.chy.dao.ds1.User1Repository;
import com.chy.dao.ds2.User2Repository;
import com.chy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by chy on 2018/11/6.
 */
@Service
public class UserService {

    @Autowired
    private User1Repository user1Repository;
    @Autowired
    private User2Repository user2Repository;

    public List<User> list(User user){

        List<User> list = user1Repository.findAll();
        list.addAll(user2Repository.findAll());

        return list;
    }

    @Transactional(rollbackOn = Exception.class)
    public List<User> save(User user) throws Exception {

        user.setStatus(1);
        user1Repository.save(user);
        if(true) throw new Exception("test exception");
        user.setStatus(2);
        user2Repository.save(user);

        return list(user);
    }
}
