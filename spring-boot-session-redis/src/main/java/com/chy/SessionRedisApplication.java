package com.chy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by chy on 2018/11/6.
 * spring session 实现
 * 基于redis
 */
@SpringBootApplication
@RestController
public class SessionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionRedisApplication.class, args);
    }

    @RequestMapping("/get")
    public String get(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        if(session != null){
            Object user = session.getAttribute("user");
            if(user == null){
                String value = "spring-session-redis" + "-" + new Date().getTime();
                session.setAttribute("user", value);
                return value;
            }
            return user.toString();
        }

        return null;
    }


}
