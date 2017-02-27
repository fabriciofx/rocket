package com.github.fabriciofx.rocket.password;

public final class PasswordInvalidException extends IllegalArgumentException {
	private static final long serialVersionUID = 3643655001060231528L;
	
	public PasswordInvalidException() {
		super("invalid password (null or empty)");
	}
}
