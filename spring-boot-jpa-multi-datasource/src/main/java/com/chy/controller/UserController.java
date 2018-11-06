package com.chy.controller;

import com.chy.domain.User;
import com.chy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chy on 2018/11/6.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list(User user){
        return userService.list(user);
    }

    @RequestMapping("/save")
    public List<User> save(User user) throws Exception{
        return userService.save(user);
    }

}
