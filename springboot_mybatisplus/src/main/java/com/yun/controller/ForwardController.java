package com.yun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ fileName:ForwardController
 * @ description:
 * @ author:zyk
 * @ createTime:2021/11/25 15:05
 * @ version:1.0.0
 */
@Controller
public class ForwardController {

    @RequestMapping("mm")
    public String mm(){
        return "mm";
    }

    @RequestMapping("test")
    public String test(){
        return "tester";
    }

    @RequestMapping("toTest")
    public String toTest(){
        return "test";
    }
}
