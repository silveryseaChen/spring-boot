package com.chy.controller;

import com.chy.model.User;
import com.chy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chy on 2018/10/8.
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<User> list(User user){

        return userService.list(user);

    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public List<User> page(User user){

        return userService.page(user);

    }

    @RequestMapping(value = "/diy", method = RequestMethod.GET)
    @ResponseBody
    public String diy(){

        return userService.diy();

    }

}
