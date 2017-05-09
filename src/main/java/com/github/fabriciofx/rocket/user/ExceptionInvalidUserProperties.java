package com.github.fabriciofx.rocket.user;

public final class ExceptionInvalidUserProperties extends
	IllegalArgumentException {
	private static final long serialVersionUID = 7144363098430435649L;
	
	public ExceptionInvalidUserProperties(final String name) {
		super(String.format("invalid properties of user '%s'", name));
	}
}
