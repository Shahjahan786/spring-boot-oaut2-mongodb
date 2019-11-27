package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.User;
import com.example.demo.models.UserRepository;
import com.example.demo.security.MongoAccessToken;
import com.example.demo.security.MongoClientDetails;
import com.example.demo.security.MongoRefreshToken;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		System.out.println("Hello to Spring boot");
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		
		mongoTemplate.dropCollection(User.class);
		mongoTemplate.dropCollection(MongoClientDetails.class);
		mongoTemplate.dropCollection(MongoAccessToken.class);
		mongoTemplate.dropCollection(MongoRefreshToken.class);
		
		if (this.userRepository.findByUsername("samoon") == null) {
			User user = new User("Shahjahan Samoon", "samoon", passwordEncoder.encode("L@ksol123"), Arrays.asList("ADMIN"));
			user.enabled = true;
			this.userRepository.save(user);
		}
	
	
		
	
		
		HashSet<String> scopeSet = new HashSet<>();
		scopeSet.add("all");
		
		HashSet<String> grantTypeSet = new HashSet<>(Arrays.asList("password", "client_credentials", "refresh_token"));
	
		
		HashSet<String> resourceIds = new HashSet<>(Arrays.asList("rokin-application"));
		
		
		
		 // init the client details
        MongoClientDetails clientDetails = new MongoClientDetails();
        clientDetails.setClientId("test-client");
        clientDetails.setClientSecret(passwordEncoder.encode("secret"));
        clientDetails.setAuthorizedGrantTypes(grantTypeSet);
        clientDetails.setAuthorities(AuthorityUtils.createAuthorityList("ADMIN"));
        clientDetails.setScope(scopeSet);
        clientDetails.setAccessTokenValiditySeconds(3600);
        clientDetails.setRefreshTokenValiditySeconds(86400);
        clientDetails.setScoped(true);
        mongoTemplate.save(clientDetails);

	}
	
	@Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
                                       MongoMappingContext context) {

        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

        return mongoTemplate;

    }

		

}
