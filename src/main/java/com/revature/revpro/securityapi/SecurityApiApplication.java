package com.revature.revpro.securityapi;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpro.securityapi.service.AccountUserDetailsService;
import com.revature.revpro.securityapi.service.CustomUser;

@SpringBootApplication
@EntityScan(
        basePackageClasses = {SecurityApiApplication.class, Jsr310JpaConverters.class}
)
@PropertySource({ "classpath:security-module.properties" })
@RestController
//@EnableOAuth2Sso
public class SecurityApiApplication {

	@Autowired
	private AccountUserDetailsService accountUserDetailsService;
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityApiApplication.class, args);
	}
	
	
	@GetMapping("/user")	
	public Principal user(Principal user) {
		System.out.println("Current User:" + user );
		return user;
	}
	
	@GetMapping("/currentuser")
	public CustomUser getCurrentUser(OAuth2Authentication oauth2) {
	
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//OAuth2Authentication oauth2 = (OAuth2Authentication) user;
		System.out.println("Approved:" + oauth2.isAuthenticated());
		String email = (String) oauth2.getPrincipal();
		CustomUser userDetails = (CustomUser)accountUserDetailsService.loadUserByUsername(email);
		System.out.println("Get User:" + userDetails);
		return userDetails;
	}
	

    /*@Bean
    OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory templateFactory) {
        return templateFactory.getUserInfoRestTemplate();
    }*/
	
}
