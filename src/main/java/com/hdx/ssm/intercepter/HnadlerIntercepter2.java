package com.hdx.ssm.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/27.
 */
public class HnadlerIntercepter2 implements HandlerInterceptor {
    //进入handler之前执行
    //用户身份认证，身份授权
    //比如身份认证，没有登陆，需要此方法拦截
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
System.out.println("HnadlerIntercepter2.......preHandle");


        //renturn表示拦截，不向下执行
//        return true表示放行
        return true;
    }
//进入handler之后
    //将公用模型数据（菜单导航）传入视图，也可以在这里统一指定视图
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("HnadlerIntercepter2.......postHandle");
    }
//handler完成后，执行此方法
    //使用统一的异常处哩，统一的日志处理
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("HnadlerIntercepter2.......afterCompletion");
    }
}
