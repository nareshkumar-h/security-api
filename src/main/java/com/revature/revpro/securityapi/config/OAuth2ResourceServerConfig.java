package com.revature.revpro.securityapi.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	static final String RESOURCES_IDS = "testjwtresourceid";

	@Override
	public void configure(ResourceServerSecurityConfigurer config) {
		config.tokenStore(tokenStore).resourceId(RESOURCES_IDS);
	}

	@Autowired
	private TokenStore tokenStore;

	//@Autowired
	//private TokenEnhancer tokenEnhancer;

	@Autowired
	private JwtAccessTokenConverter tokenConverter;

	//@Bean
	public ResourceServerTokenServices defaultTokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenEnhancer(tokenConverter);
		defaultTokenServices.setTokenStore(tokenStore);
		
		return defaultTokenServices;
	}

	/*
	 * @Bean
	 * 
	 * @Primary public DefaultTokenServices tokenServices() { DefaultTokenServices
	 * defaultTokenServices = new DefaultTokenServices();
	 * defaultTokenServices.setTokenStore(tokenStore()); return
	 * defaultTokenServices; }
	 */

	/*
	 * @Override public void configure(HttpSecurity http) throws Exception {
	 * System.out.println("configure : " + http); http.requestMatcher(new
	 * OAuthRequestedMatcher()).authorizeRequests().antMatchers(HttpMethod.OPTIONS).
	 * permitAll() .anyRequest().authenticated(); }
	 */

	private static class OAuthRequestedMatcher implements RequestMatcher {
		public boolean matches(HttpServletRequest request) {
			String auth = request.getHeader("Authorization"); // Determine if the client request contained an OAuth
																// Authorization
			System.out.println("Fetching Auth:" + auth);
			boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
			System.out.println("HaveOauth2Token:" + haveOauth2Token);
			boolean haveAccessToken = request.getParameter("access_token") != null;
			return haveOauth2Token || haveAccessToken;
		}
	}

	public OAuth2Authentication loadAuthentication(String accessToken) {
		System.out.println("Oauth2Authentication:" + accessToken);
		return null;
	}

}
