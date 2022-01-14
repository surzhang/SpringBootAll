package com.yun.controller;

import com.yun.entity.ReturnBean;
import com.yun.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ fileName:LoginController
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/16 13:17
 * @ version:1.0.0
 */
@Controller
@RequestMapping
public class LoginController {
    @RequestMapping("login")
    @ResponseBody
    public ReturnBean login(HttpSession session, @RequestBody User user){
        if (user==null){
            return new ReturnBean(1,"操作失败",null,null);
        }else{
            session.setAttribute("token",user.toString());
            return new ReturnBean(0,"操作成功",null,null);
        }
    }
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("toShowDept")
    public String toShowDept(){
        return "index";
    }

    @RequestMapping("showUpload")
    public String toFile(){
        return "upload/showUpload";
    }
}
