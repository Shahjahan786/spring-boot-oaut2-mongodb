package com.example.demo.security;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PreSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        
        String requestURI = request.getRequestURI();
        
        String deviceId = (String) request.getParameter("deviceId");
        
        if(requestURI.contains("/oauth/token") && (deviceId == null || "".contentEquals(deviceId))) {
        	throw new InvalidParameterException("deviceId must be provided!");
        }
        
        filterChain.doFilter(request, response);            
       

    }
}
