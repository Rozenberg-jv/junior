package by.kolbun.study.junior.exceptions;

public class LoginFailedException extends RuntimeException {

	public LoginFailedException() {

	}

	public LoginFailedException(String message) {

		super(message);
	}
}
