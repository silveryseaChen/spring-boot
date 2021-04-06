package com.chy.service;

import com.chy.common.BaseService;
import com.chy.mapper.UserMapper;
import com.chy.model.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chy on 2018/10/8.
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    public List<User> list(User user){
        userMapper.selectCountByExample(user);
        return userMapper.select(user);
    }

    public List<User> page(User user) {

        PageHelper.startPage(1, 2);
        return userMapper.select(user);
    }

    public String diy() {
        PageHelper.startPage(1, 2);
        return userMapper.diyXml()+"\t"+userMapper.diyAnnotation();
    }
}
