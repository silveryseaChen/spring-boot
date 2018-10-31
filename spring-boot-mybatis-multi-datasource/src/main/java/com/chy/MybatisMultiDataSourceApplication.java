package com.chy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by chy on 2017/5/7.
 * 基于 mybatis 多数据源
 * 通过不同mapper 目录 区分不同数据源
 */
@SpringBootApplication
public class MybatisMultiDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMultiDataSourceApplication.class, args);
    }

}
