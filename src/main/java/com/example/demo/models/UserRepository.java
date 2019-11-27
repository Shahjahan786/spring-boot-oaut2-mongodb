package com.example.demo.models;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, Long>{

	@Query("{$or:[{name : { $regex: ?0 , $options: 'i' }}, {username : { $regex: ?0 , $options: 'i' }}]}")
    List<User> findUserByName(String name);
	
	User findByUsername(String username);
	
}
