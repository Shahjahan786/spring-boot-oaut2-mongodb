package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ApiResponse;
import com.example.demo.models.User;
import com.example.demo.models.UserRepository;

@RestController
public class MainController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@RequestMapping(method = RequestMethod.GET, value="/user/{name}")
	public ApiResponse<List<User>> findUserById(@PathVariable("name") String name) {
		List<User> users =  userRepository.findUserByName(name);
		
		if(users.isEmpty()) {
			throw new RuntimeException("No data found!");
		}
		
		ApiResponse<List<User>> response = new ApiResponse<List<User>>("1", "Success", users);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/user/add/{name}/{username}")
	public ApiResponse<User> addNewUser(@PathVariable("name") String name, @PathVariable("username") String username) {
		User user = new User();
		user.name = name;
		user.username = username;
		
		User newUser = userRepository.save(user);
		return new ApiResponse<User>("1", "Success", newUser);
	
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/usersall")
	public ApiResponse<List<User>> findAll() {
		
		List<User> users =  mongoTemplate.findAll(User.class);
		
		if(users.isEmpty()) {
			ApiResponse<List<User>> response = new ApiResponse<List<User>>("0", "No Data found", users);
			return response;
		}
		
		ApiResponse<List<User>> response = new ApiResponse<List<User>>("1", "Success", users);
		return response;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
