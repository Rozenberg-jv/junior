package by.kolbun.study.junior.configuration.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

	private final JWTTokenComponent tokenComponent;

	private final UserDetailsService userDetailsService;

	TokenAuthenticationFilter(JWTTokenComponent tokenComponent, UserDetailsService userDetailsService) {

		this.tokenComponent = tokenComponent;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		final String token = tokenComponent.getToken(request);
		String username = null;

		try {
			if (token != null) {
				try {
					username = tokenComponent.getUsernameFromToken(token);
				} catch (IllegalArgumentException e) {
					LOG.error("Unable to get JWT Token");
				} catch (ExpiredJwtException e) {
					LOG.error("JWT Token has expired");
				}
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if (tokenComponent.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		} catch (JwtException e) {
			LOG.debug("Invalid token", e);
		}

		filterChain.doFilter(request, response);
	}
}
