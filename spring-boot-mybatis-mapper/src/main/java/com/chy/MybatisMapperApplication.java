package com.chy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by chy on 2017/11/27.
 * 使用工具包 mapper page-helper
 */
@SpringBootApplication
@MapperScan(basePackages = "com.chy.mapper")
public class MybatisMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMapperApplication.class, args);
    }

}
