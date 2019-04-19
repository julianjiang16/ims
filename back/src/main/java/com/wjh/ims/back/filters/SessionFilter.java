package com.wjh.ims.back.filters;

import com.wjh.ims.back.Constant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2015-2018
 * FileName: SessionFilter
 * Author:   jcj
 * Date:     2018/9/18 17:34
 * Description: 过滤器
 */
public class SessionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse res=(HttpServletResponse)servletResponse;
        // 1.获取请求url
        String url=req.getRequestURL().toString();
        // 2.判断请求是否为静态资源
        boolean flag=url.contains("login")||url.contains(".js")||url.contains(".html")||url.contains(".css")||
                url.contains(".png")||url.contains(".jpg")||url.contains(".gif")||url.contains(".mp4");
        if(!flag){
            if(req.getSession().getAttribute(Constant.COOKIE_USER_KEY)==null){//未登录
                String ajax = req.getHeader("x-Requested-with");
                // 处理ajax 请求
                if(ajax!=null&&ajax.equals("XMLHttpRequest")){
                    res.setHeader("sessionInvalid","timeout");
                }else
                    res.sendRedirect(req.getContextPath()+"/login.html");
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    public void destroy() {

    }
}
