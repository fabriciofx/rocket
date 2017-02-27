package com.github.fabriciofx.rocket.password;

public final class PasswordPlain implements Password {
	private final String content;
	
	public PasswordPlain(final String content) {
		this.content = content;
	}
	
	@Override
	public String content() {
		return this.content;
	}
}
