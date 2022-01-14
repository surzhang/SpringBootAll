package com.yun.controller;

import com.yun.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName: HelloController
 * @Description:
 * @Author: zyk
 * @createTime: 2021/12/24 9:38
 * @version: 1.0
 */
@RestController
public class HelloController {
    @PostMapping("/hello")
    public String helloworld(){
        return "hello world";
    }
    @PostMapping(value = "/user")
    public User user(){
        User user = new User("张三",18,"2222");
        return user;
    }
}
