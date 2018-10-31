package com.chy.service;

import com.chy.mapper.UserMapper;
import com.chy.model.User;
import com.chy.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by chy on 2018/10/8.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> list(User user){
        UserExample userExample = new UserExample();
        if(!StringUtils.isEmpty(user.getName())){
            userExample.createCriteria().andNameEqualTo(user.getName());
        }
        List<User> list = userMapper.selectByExample(userExample);

        return list;
    }

}
