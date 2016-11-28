package com.hdx.ssm.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登陆人认证拦截器
 * Author: lovegod
 * Created by 123 on 2016/11/27.
 */
public class LoginIntercepter implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
      //获取请求url
        String url=httpServletRequest.getRequestURI();
        //判断url是否为公开地址
        //这里公开地址就是登陆提交的地址
        if(url.indexOf("login.form")>0){
            return true;
        }
        //判断session
        HttpSession session=httpServletRequest.getSession();
        //从session中取出用户的身份信息
        String username= (String) session.getAttribute("username");
        if(username!=null){
            //身份验证通过，放行
            return  true;
        }
        //执行到这里表示用户身份需要认证，跳转登陆界面
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(httpServletRequest,httpServletResponse);

        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
