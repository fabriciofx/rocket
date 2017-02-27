package com.github.fabriciofx.rocket.user;

public final class UserPropertiesInvalidException extends
	IllegalArgumentException {
	private static final long serialVersionUID = 7144363098430435649L;
	
	public UserPropertiesInvalidException(final String name) {
		super(
			String.format("invalid properties of user '%s'", name)
		);
	}
}
