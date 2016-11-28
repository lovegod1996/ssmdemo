package com.hdx.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/27.
 */
@Controller
public class LoginController {
    //登陆
    @RequestMapping("/login")
    public String login(HttpSession httpSession,String username, String password)throws Exception{
        //调用service进行校验
        //。。
        httpSession.setAttribute("username",username);
        //重定向到商品列表界面
        return "redirect:/items/queryItems.form";
    }
    //退出
    @RequestMapping("/logout")
    public String logout(HttpSession httpSession)throws Exception{
       //清楚session
        httpSession.invalidate();
        //重定向到商品列表界面
        return "redirect:/items/queryItems.form";
    }
}
