package com.example.demo.security;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

public class AccessTokenImpl implements OAuth2AccessToken{
	
	AccessToken accessToken;
	
	AccessTokenImpl(AccessToken accessToken ){
		this.accessToken = accessToken;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return accessToken.getAdditionalInformation();
	}

	@Override
	public Set<String> getScope() {
		// TODO Auto-generated method stub
		return accessToken.getScope();
	}

	@Override
	public OAuth2RefreshToken getRefreshToken() {
		// TODO Auto-generated method stub
		return accessToken.getRefreshToken();
	}

	@Override
	public String getTokenType() {
		// TODO Auto-generated method stub
		return accessToken.getTokenType();
	}

	@Override
	public boolean isExpired() {
		// TODO Auto-generated method stub
		return accessToken.isExpired();
	}

	@Override
	public Date getExpiration() {
		// TODO Auto-generated method stub
		return accessToken.getExpiration();
	}

	@Override
	public int getExpiresIn() {
		// TODO Auto-generated method stub
		return accessToken.getExpiresIn();
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return accessToken.getValue();
	}

}
