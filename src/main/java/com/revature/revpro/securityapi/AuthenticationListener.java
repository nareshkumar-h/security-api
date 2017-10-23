package com.revature.revpro.securityapi;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.revature.revpro.securityapi.service.CustomUser;

@Component
public class AuthenticationListener implements ApplicationListener<AuthenticationSuccessEvent>{

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		
		//CustomUser ud = (CustomUser) event.getAuthentication().getPrincipal();
		System.out.println("Authentication Listener - auth success event->" );
		
	}

}
