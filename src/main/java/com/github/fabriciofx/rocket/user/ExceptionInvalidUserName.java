package com.github.fabriciofx.rocket.user;

public final class ExceptionInvalidUserName extends IllegalArgumentException {
	private static final long serialVersionUID = -2570476401295380209L;
	
	public ExceptionInvalidUserName() {
		super("invalid user name (null) or (empty)");
	}
}
