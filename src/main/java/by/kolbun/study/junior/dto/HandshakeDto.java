package by.kolbun.study.junior.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class HandshakeDto {

	private final String token;

	@JsonCreator
	public HandshakeDto(@JsonProperty String token) {

		this.token = token;
	}

}
