package com.example.demo.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(collection = "user")
public class User implements UserDetails{
	
	@Id
	private String id;
	
	public String name;
	
	
	public String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private List<String> roles;
	
	public boolean enabled;
	public boolean accountNonExpired;
	public boolean accountNonLocked;
	public boolean credentialsNonExpired;
	
	public User() {

	}

	public User(String name, String username, String password, List<String> roles) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", roles="
				+ roles + "]";
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
	    for (String role : getRoles()) {
	        authorities.add(new SimpleGrantedAuthority(role));
	    }
	    return authorities;
	}

	public boolean isAccountNonExpired() {
	    // TODO Auto-generated method stub
	    return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
	    // TODO Auto-generated method stub
	    return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
	    // TODO Auto-generated method stub
	    return credentialsNonExpired;
	}

	public boolean isEnabled() {
	    // TODO Auto-generated method stub
	    return enabled;
	}
	

}
