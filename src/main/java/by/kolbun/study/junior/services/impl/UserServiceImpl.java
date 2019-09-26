package by.kolbun.study.junior.services.impl;

import by.kolbun.study.junior.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

	private final User superuser;

	@Autowired
	public UserServiceImpl(@Value("${superuser.user}") String superuser, @Value("${superuser.password}") String password) {

		this.superuser = new User(superuser, DigestUtils.sha256Hex(password), Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
	}

	@Override
	public User getUser(String username) {

		return superuser;
	}
}
