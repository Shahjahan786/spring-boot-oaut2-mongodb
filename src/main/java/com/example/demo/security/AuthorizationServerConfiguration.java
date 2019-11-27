package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;
	
	// @Bean
	 public MongoClientDetailsService clientDetailsService() {
	     return new MongoClientDetailsService();
	 }

	//@Bean
	 public AuthorizationCodeServices authorizationCodeServices() {
	      return new MongoAuthorizationCodeServices();
	    
	 }
	
	
	
	

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		security.allowFormAuthenticationForClients(); //For authenticating client using the form parameters instead of basic auth 
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		 clients.withClientDetails(clientDetailsService());
		
		
		clients.inMemory()
		.withClient("test-client")
		.secret(passwordEncoder.encode("secret"))
		.authorizedGrantTypes("password", "client_credentials", "refresh_token")
		.scopes("all")
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(86400);
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
		 	.authorizationCodeServices(authorizationCodeServices())
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService)
			.tokenServices(tokenServices())
		    .exceptionTranslator(customWebResponseExceptionTranslator);
	}

	@Bean
	public TokenStore tokenStore() {
		return new MongoTokenStore();
		//return new InMemoryTokenStore();
	}
	
	@Primary
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());

        List<TokenEnhancer> enhancers = new ArrayList<>();
      
        //Some custom enhancer
        enhancers.add(new CustomTokenEnhancer());

        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(enhancers);
        tokenServices.setTokenEnhancer(enhancerChain);

        return tokenServices;
    }


}