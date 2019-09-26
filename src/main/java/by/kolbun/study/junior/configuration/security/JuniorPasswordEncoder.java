package by.kolbun.study.junior.configuration.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JuniorPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {

		return DigestUtils.sha256Hex(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {

		String encrypted = encode(rawPassword);
		System.out.println("encrypted: " + encrypted);
		return encrypted.equals(encodedPassword);
	}
}
