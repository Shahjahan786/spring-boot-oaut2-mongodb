package com.example.demo.security;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class AccessToken {
	
	public Map<String, Object> additionalInformation;
	public Set<String> scope;
	
	public RefreshToken refreshToken;
	public String tokenType;
	public boolean isExpired;
	public Date expiration;
	public int expiresIn;
	public String value;
	
	public AccessToken() {
		
	}
	
	@JsonIgnore
	public static AccessToken getAccessToken(OAuth2AccessToken token) {
		
		AccessToken aToken = new AccessToken();
		
		aToken.additionalInformation = token.getAdditionalInformation();
		aToken.scope = token.getScope();
		aToken.tokenType = token.getTokenType();
		aToken.isExpired = token.isExpired();
		aToken.expiration = token.getExpiration();
		aToken.expiresIn = token.getExpiresIn();
		aToken.value = token.getValue();
		aToken.refreshToken = new RefreshToken(token.getRefreshToken().getValue());
		return aToken;
	}
	
	
	
	
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return additionalInformation;
	}

	public Set<String> getScope() {
		// TODO Auto-generated method stub
		return scope;
	}

	public RefreshToken getRefreshToken() {
		// TODO Auto-generated method stub
		return refreshToken;
	}

	public String getTokenType() {
		// TODO Auto-generated method stub
		return tokenType;
	}

	public boolean isExpired() {
		// TODO Auto-generated method stub
		return isExpired;
	}

	public Date getExpiration() {
		// TODO Auto-generated method stub
		return expiration;
	}

	public int getExpiresIn() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	
	
}
