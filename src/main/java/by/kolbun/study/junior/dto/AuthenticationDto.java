package by.kolbun.study.junior.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AuthenticationDto {

	private final String username;
	private final String password;

	@JsonCreator
	public AuthenticationDto(@JsonProperty("username") String username, @JsonProperty("password") String password) {

		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {

		return String.format("[username: %s, password: %s]", username, password);
	}

}
