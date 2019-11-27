package com.example.demo.security;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.demo.models.ApiResponse;

@ControllerAdvice
public class OauthReponseAdvice implements ResponseBodyAdvice<Object> {
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		ServletServerHttpResponse mResponse = (ServletServerHttpResponse) response;
		int status = mResponse.getServletResponse().getStatus();

		String message = status == 200 ? "Success" : "Failure";

		System.out.println("OauthReponseAdvice called..." + status);
		if (body instanceof OAuth2AccessToken) {
			ApiResponse<Object> resp = new ApiResponse<>(String.valueOf(status), message, body, status != 200);
			return resp;
		}

		if (!(body instanceof Exception) && !(body instanceof ApiResponse) && !(body instanceof byte[])) {
			ApiResponse<Object> resp = new ApiResponse<>(String.valueOf(status), message, body, status != 200);
			return resp;
		}

		/// Modify body here
		return body;
	}
}