package com.example.jwtserver.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpServletResponse res=(HttpServletResponse)servletResponse;
        if(req.getMethod().equals("POST")){
            String headerAuth=req.getHeader("Authorization");
            System.out.println(headerAuth);
            if(headerAuth.equals("cos")){
                filterChain.doFilter(req,res);
            } else{
                PrintWriter out=res.getWriter();
                out.println("인증안됨");
            }
        }
        System.out.println("Filter1");
    }
}
