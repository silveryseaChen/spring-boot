package com.chy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by chy on 2018/10/31.
 *
 * 一个主库 多个从库
 * 从库可以根据指定规则进行切换
 */
@SpringBootApplication
public class MasterSlavesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterSlavesApplication.class, args);
    }

}
