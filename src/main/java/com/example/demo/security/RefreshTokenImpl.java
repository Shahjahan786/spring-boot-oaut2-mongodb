package com.example.demo.security;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;

public class RefreshTokenImpl implements OAuth2RefreshToken{
	
	String value;
	
	RefreshTokenImpl(RefreshToken token){
		this.value = token.getValue();
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

}
