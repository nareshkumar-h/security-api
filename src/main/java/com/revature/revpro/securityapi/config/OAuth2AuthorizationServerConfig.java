package com.revature.revpro.securityapi.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String RESOURCES_IDS = "testjwtresourceid";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter)
				// .tokenEnhancer(tokenEnhancer())
				.authenticationManager(authenticationManager);
	}
/*
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}*/

	@Bean
    @Qualifier("tokenStore")
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray())
				.getKeyPair("mytest");
		converter.setKeyPair(keyPair);
		return converter;
	}

	// @Autowired
	// private TokenStore tokenStore;
	/*
	 * @Bean public TokenStore tokenStore() { return new
	 * JwtTokenStore(accessTokenConverter()); }
	 */
	/*
	 * @Bean public JwtAccessTokenConverter accessTokenConverter() {
	 * JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	 * converter.setSigningKey("123"); return converter; }
	 */

	/*
	 * @Bean public JwtAccessTokenConverter accessTokenConverter() {
	 * JwtAccessTokenConverter converter = new JwtAccessTokenConverter(); KeyPair
	 * keyPair = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"),
	 * "mypass".toCharArray()) .getKeyPair("mytest"); converter.setKeyPair(keyPair);
	 * return converter; }
	 */

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		//defaultTokenServices.setTokenEnhancer(tokenEnhancer());
		return defaultTokenServices;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("client").secret("secret")
				.authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes(SCOPE_READ, SCOPE_WRITE)
				.resourceIds(RESOURCES_IDS).accessTokenValiditySeconds(60);
	}

	/*
	 * @Override public void configure(AuthorizationServerSecurityConfigurer
	 * oauthServer) throws Exception {
	 * oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess(
	 * "isAuthenticated()"); }
	 */
}