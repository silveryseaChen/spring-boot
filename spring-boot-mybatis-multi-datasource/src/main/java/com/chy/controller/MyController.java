package com.chy.controller;

import com.chy.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chy on 2017/5/5.
 */
@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/get")
    public String get(){
        int cnt = myService.get();
        return "hello my springboot! "+cnt;
    }

    @RequestMapping(value = "/update")
    public String update() throws Exception{
        int cnt = myService.update();
        return "hello my springboot! update " + cnt;
    }

}
