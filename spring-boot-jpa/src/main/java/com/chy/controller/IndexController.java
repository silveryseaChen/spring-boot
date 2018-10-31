package com.chy.controller;

import com.chy.model.User;
import com.chy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by chy on 2017/11/25.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all")
    @ResponseBody
    public List<User> all(){

        return userService.all();

    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public User insert() throws Exception{

        return userService.testTransaction();

    }

    @GetMapping(value = "/list")
    @ResponseBody
    public List<User> list() throws Exception{

        return userService.diySql();

    }

}
