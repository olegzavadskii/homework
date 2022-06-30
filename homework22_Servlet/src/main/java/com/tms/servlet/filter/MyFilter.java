package com.tms.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpSR = (HttpServletRequest) servletRequest;
        String user = httpSR.getHeader("user");
        if (user == null) {
            PrintWriter writer = servletResponse.getWriter();
            writer.println("Header USER is empty");
            String method = httpSR.getMethod();
            if (method.equalsIgnoreCase("GET")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
