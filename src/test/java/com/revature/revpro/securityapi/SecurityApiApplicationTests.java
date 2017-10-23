package com.revature.revpro.securityapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApiApplicationTests {

	@Autowired
	JwtAccessTokenConverter tokenConverter;
	
	@Value("${security.oauth2.resource.jwt.keyValue}")
	public String key;
	
	private JsonParser objectMapper = JsonParserFactory.create();
	
	@Test
	public void contextLoads() {

		String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDg1NDQ4MzAsInVzZXJfbmFtZSI6Im5hcmVzaEBnbWFpbC5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIl0sImp0aSI6IjIyNzFhY2I4LTY5NmEtNDIyNy04NzA1LWEyNTQ4YmQ2OTgxOSIsImNsaWVudF9pZCI6ImNsaWVudCIsInNjb3BlIjpbIm9wZW5pZCJdfQ.BAyiuuL1wknGQs1OGbP_PGcLLoMCGpqSI4ip4p_vmmQ";

		
		System.out.println("Key=" + key);
		//tokenConverter.convertAccessToken(token, authentication);
		System.out.println("AccessToken=" + accessToken);
		
		System.out.println(tokenConverter);

	}
	
	/*protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		String content;
		try {
			content = objectMapper.formatMap(tokenConverter.convertAccessToken(accessToken, authentication));
		}
		catch (Exception e) {
			throw new IllegalStateException("Cannot convert access token to JSON", e);
		}
		String token = JwtHelper.encode(content, signer).getEncoded();
		return token;
	}*/


}
