package by.kolbun.study.junior.configuration.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class TokenProperties {

	private String header;

	private String prefix;

	private String secret;

	private long lifeTime;

	private String responseHeader;

	public TokenProperties() {

	}

	public String getHeader() {

		return header;
	}

	public void setHeader(String header) {

		this.header = header;
	}

	public String getPrefix() {

		return prefix;
	}

	public void setPrefix(String prefix) {

		this.prefix = prefix;
	}

	public String getSecret() {

		return secret;
	}

	public void setSecret(String secret) {

		this.secret = secret;
	}

	public long getLifeTime() {

		return lifeTime;
	}

	public void setLifeTime(long lifeTime) {

		this.lifeTime = lifeTime;
	}

	public String getResponseHeader() {

		return responseHeader;
	}

	public void setResponseHeader(String responseHeader) {

		this.responseHeader = responseHeader;
	}
}
