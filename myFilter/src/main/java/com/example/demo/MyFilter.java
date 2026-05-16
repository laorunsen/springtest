package com.example.demo;

import java.io.IOException;
import java.util.Objects;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpreRequest = (HttpServletRequest)request;
        String ip = httpreRequest.getRemoteAddr();
        HttpSession session = httpreRequest.getSession();
        Integer count = (Integer)session.getAttribute("count");
        count = Objects.isNull(count) ? 1 : ++count;
        session.setAttribute("count", count);
        System.out.println("ip :" + ip + " count: " + count);

        chain.doFilter(request, response);
    }
}
