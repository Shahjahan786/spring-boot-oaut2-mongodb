package com.example.demo.models;

public class ApiResponse<T> {

	public String status;
	public String message;
	
	public T data;
	public T error;

	public ApiResponse(String status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		
		if(status != null && !status.contentEquals("0")) {
			this.data = data;
		}else {
			this.error = data;
		}
		
	}
	
	public ApiResponse(String status, String message, T data, boolean isError) {
		super();
		this.status = status;
		this.message = message;
		
		if(!isError) {
			this.data = data;
		}else {
			this.error = data;
		}
		
	}
	
	
	
	
}
