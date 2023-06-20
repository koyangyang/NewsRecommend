package com.haust.back.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)
    {
        System.out.println(
                "拦截器拦截请求："+request.getMethod()+" 请求路径："+request.getRequestURI()+" 请求ip："+request.getRemoteAddr()+" 请求主机名："
                +request.getLocalName()+"请求认证通过"
        );
        return true;
    }
}
