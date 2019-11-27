package com.example.demo.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer{

	@Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Authentication userAuthentication = authentication.getUserAuthentication();

        final DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
        Set<String> existingScopes = new HashSet<>();
        existingScopes.addAll(defaultOAuth2AccessToken.getScope());
        if (userAuthentication != null) {
        	
            //User has logged into system
            existingScopes.add("read-foo");
            
           AuthUser authUser = (AuthUser) userAuthentication.getPrincipal();
           Map<String, Object> info = new HashMap<>(defaultOAuth2AccessToken.getAdditionalInformation());
           
           info.put("user", authUser.getUser().getName());
           defaultOAuth2AccessToken.setAdditionalInformation(info);
           
            
        } else {
            //service is trying to access system
            existingScopes.add("another-scope");
        }

        defaultOAuth2AccessToken.setScope(existingScopes);
        return defaultOAuth2AccessToken;
    }
	
}
