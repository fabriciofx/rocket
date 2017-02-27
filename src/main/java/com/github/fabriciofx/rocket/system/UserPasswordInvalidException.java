package com.github.fabriciofx.rocket.system;

public final class UserPasswordInvalidException extends
	IllegalArgumentException {
	private static final long serialVersionUID = 7363470873514615125L;

	public UserPasswordInvalidException(final String password) {
		super(
			String.format("invalid user password '%s'", password)
		);
	}
}
