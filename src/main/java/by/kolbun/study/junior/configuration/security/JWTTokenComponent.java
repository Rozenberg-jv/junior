package by.kolbun.study.junior.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JWTTokenComponent {

	private final TokenProperties properties;

	@Autowired
	public JWTTokenComponent(TokenProperties properties) {

		this.properties = properties;
	}

	String getToken(HttpServletRequest request) {

		String header = request.getHeader(properties.getHeader());
		String prefix = properties.getPrefix();
		if (Objects.nonNull(header) && !header.isEmpty() && header.startsWith(prefix)) {

			//prefix with space char
			return header.substring(prefix.length() + 1);
		}

		return null;
	}

	String getUsernameFromToken(String token) {

		return getClaimFromToken(token, Claims::getSubject);
	}

	private Date getExpirationDateFromToken(String token) {

		return getClaimFromToken(token, Claims::getExpiration);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {

		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	String generateToken(UserDetails userDetails) {

		final Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder()
				   .setClaims(claims)
				   .setSubject(subject)
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + properties.getLifeTime() * 1000))
				   .signWith(SignatureAlgorithm.HS512, properties.getSecret())
				   .compact();
	}

	Boolean validateToken(String token, UserDetails userDetails) {

		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	void applyToken(String token, HttpServletResponse response) {

		response.setHeader(properties.getResponseHeader(), token);
	}
}