package by.kolbun.study.junior.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

	UserDetails getUser(String username);
}
