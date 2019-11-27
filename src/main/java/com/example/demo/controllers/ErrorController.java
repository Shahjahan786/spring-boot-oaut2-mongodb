package com.example.demo.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.models.ApiResponse;

@ControllerAdvice
@RestController
@RequestMapping("/error")
public class ErrorController extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = Exception.class)
	public ApiResponse<Exception> exception(Exception exception) {
		return new ApiResponse<Exception>("0", exception.getMessage(), exception);
	}
	
}
