package com.chy.controller;

import com.chy.configure.MyMetricks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * order
 *
 * Created by chy on 21/4/1.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    List<String> orders = new ArrayList<>();

    @Autowired
    private MyMetricks myMetricks;

    @GetMapping
    public List get(){
        return orders;
    }

    @PostMapping
    public boolean add(@RequestParam("order") String order){
        myMetricks.regist();
        return orders.add(order);
    }

    @DeleteMapping
    public boolean delete(String order){
        return orders.remove(order);
    }

}
