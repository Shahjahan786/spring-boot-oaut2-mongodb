package com.example.demo.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.demo.models.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
            Map<String, String> map = new HashMap<>();
            map.put("error1", "400");
            map.put("message", accessDeniedException.getMessage());
            map.put("path1", request.getServletPath());
            map.put("timestamp1", String.valueOf(new Date().getTime()));
            
            ApiResponse< Map<String, String>> error = new  ApiResponse<>("400", accessDeniedException.getMessage(), map, true);
            
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}
