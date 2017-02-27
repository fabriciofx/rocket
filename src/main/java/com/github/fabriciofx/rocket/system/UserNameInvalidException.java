package com.github.fabriciofx.rocket.system;

public final class UserNameInvalidException extends IllegalArgumentException {
	private static final long serialVersionUID = -2570476401295380209L;
	
	public UserNameInvalidException(final String name) {
		super(
			String.format("invalid user name '%s'", name)
		);
	}
}
