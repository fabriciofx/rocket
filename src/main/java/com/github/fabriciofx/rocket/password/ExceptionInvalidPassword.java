package com.github.fabriciofx.rocket.password;

public final class ExceptionInvalidPassword extends IllegalArgumentException {
	private static final long serialVersionUID = 3643655001060231528L;
	
	public ExceptionInvalidPassword() {
		super("invalid password (null or empty)");
	}
}
