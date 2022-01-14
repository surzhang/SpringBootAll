package com.yun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @FileName: ForwardController
 * @Description:
 * @Author: zyk
 * @createTime: 2021/12/21 18:22
 * @version: 1.0
 */
@Controller
public class ForwardController {
    @RequestMapping("toHome")
    public String home(){
        return "index";
    }
}
