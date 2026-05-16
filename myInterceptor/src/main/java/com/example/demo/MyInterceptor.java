package com.example.demo;

import java.util.Objects;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                HttpServletRequest httpreRequest = (HttpServletRequest)request;
        String ip = httpreRequest.getRemoteAddr();
        HttpSession session = httpreRequest.getSession();
        Integer count = (Integer)session.getAttribute("count");
        count = Objects.isNull(count) ? 1 : ++count;
        session.setAttribute("count", count);
        System.out.println("ip :" + ip + " count: " + count);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
