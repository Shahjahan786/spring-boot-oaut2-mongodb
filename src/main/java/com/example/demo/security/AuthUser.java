package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthUser extends User {
	
	com.example.demo.models.User dbUser;
	
	public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities, com.example.demo.models.User dbUser) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
		this.dbUser = dbUser;
	}
	
	
	public com.example.demo.models.User getUser() {
		return this.dbUser;
	}
	

	
	
	
}
