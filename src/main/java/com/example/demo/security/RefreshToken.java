package com.example.demo.security;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;

public class RefreshToken implements OAuth2RefreshToken{
	
	public String value;
	
	public RefreshToken(String value) {
		this.value = value;
	}
	

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
