package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.resource.UserApprovalRequiredException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.models.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws RuntimeException {
		User dbUser = this.userRepository.findByUsername(username);

		if (dbUser != null) {
			
			if(!dbUser.enabled) {
				OAuth2Exception ex = CustomOauthException.create(CustomOauthException.ACCOUNT_DISABLED, "Your account is disabled!");
				throw ex;
			}else  if(!dbUser.accountNonLocked) {
				
				OAuth2Exception ex = CustomOauthException.create(CustomOauthException.ACCOUNT_LOCKED, "User account is locked!");
				throw ex;
				
			}else if(!dbUser.accountNonExpired) {
				OAuth2Exception ex = CustomOauthException.create(CustomOauthException.ACCOUNT_EXPIRED, "User account is expired!");
				throw ex;
			}else if(!dbUser.credentialsNonExpired) {
				OAuth2Exception ex = CustomOauthException.create(CustomOauthException.ACCOUNT_CREDENTIALS_EXPIRED, "User credentials are expired!");
				throw ex;
				
			}
			
			Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
			
			
			for (String role : dbUser.getRoles()) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantedAuthorities.add(authority);
			}
			
			if(grantedAuthorities.isEmpty()) {
				grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
			}
					
			AuthUser user = new AuthUser(
					dbUser.getUsername(), dbUser.getPassword(), grantedAuthorities, dbUser);
			return user;
		} else {
			throw CustomOauthException.create(CustomOauthException.USER_NOT_FOUND, "Incorrect username/password!");
		}
	}


}
