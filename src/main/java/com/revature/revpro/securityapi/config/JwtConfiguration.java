package com.revature.revpro.securityapi.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class JwtConfiguration {

	/*
	 * @Autowired JwtAccessTokenConverter jwtAccessTokenConverter;
	 */

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
		//converter.enhance(accessToken, authentication)
		return converter;
	}

	/*
	 * @Bean public JwtAccessTokenConverter accessTokenConverter() {
	 * System.out.println("AccessTokenConverter:"); JwtAccessTokenConverter
	 * converter = new JwtAccessTokenConverter(); Resource resource = new
	 * ClassPathResource("public.cert"); String publicKey = null; try { publicKey =
	 * new String( FileCopyUtils.copyToByteArray( resource.getInputStream())); }
	 * catch (final IOException e) { throw new RuntimeException(e); }
	 * converter.setVerifierKey(publicKey); return converter; }
	 */

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		defaultTokenServices.setTokenEnhancer(accessTokenConverter());
		return defaultTokenServices;
	}

	/*@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}
*/
}
