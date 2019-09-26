package by.kolbun.study.junior.configuration.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenAuthentication extends UsernamePasswordAuthenticationToken {

	private final String token;

	public TokenAuthentication(UserDetails details, String token) {

		super(details.getUsername(), details.getPassword());
		this.token = token;
	}

	public String getToken() {

		return token;
	}
}
