package by.kolbun.study.junior.controllers;

import by.kolbun.study.junior.Nav;
import by.kolbun.study.junior.configuration.security.TokenAuthentication;
import by.kolbun.study.junior.dto.AuthenticationDto;
import by.kolbun.study.junior.dto.HandshakeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	private final AuthenticationManager authManager;

	@Autowired
	public AuthenticationController(AuthenticationManager authManager) {

		this.authManager = authManager;
	}

	@PostMapping(Nav.AUTHENTICATE)
	public ResponseEntity<?> authenticateWithToken(@RequestBody AuthenticationDto authenticationDto) {

		String username = authenticationDto.getUsername();
		String password = authenticationDto.getPassword();

		TokenAuthentication authentication = (TokenAuthentication) authManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password)
		);

		return ResponseEntity.ok(new HandshakeDto(authentication.getToken()));
	}
}
