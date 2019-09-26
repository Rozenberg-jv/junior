package by.kolbun.study.junior.configuration.security;

import by.kolbun.study.junior.exceptions.LoginFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

	private final UserDetailsService userDetailsService;

	private final JuniorPasswordEncoder passwordEncoder;

	private final JWTTokenComponent jwtTokenComponent;

	@Autowired
	public TokenAuthProvider(UserDetailsService userDetailsService, JuniorPasswordEncoder passwordEncoder, JWTTokenComponent jwtTokenComponent) {

		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenComponent = jwtTokenComponent;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = (String) authentication.getPrincipal();
		String rawPassword = (String) authentication.getCredentials();

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if (Objects.isNull(userDetails)) {
			throw new LoginFailedException("No such user");
		}

		String encodedPassword = userDetails.getPassword();
		System.out.println("user: " + username);
		System.out.println("rawPassword: " + rawPassword);
		System.out.println("encodedPassword: " + encodedPassword);

		boolean same = passwordEncoder.matches(rawPassword, encodedPassword);
		if (!same) {
			throw new LoginFailedException("Wrong password");
		}

		String token = jwtTokenComponent.generateToken(userDetails);

		return new TokenAuthentication(userDetails, token);
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}
}
