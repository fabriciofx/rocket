package com.github.fabriciofx.rocket.password;

public final class PasswordSafe implements Password {
	private final Password origin;
	
	public PasswordSafe(final Password origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		if (this.origin == null || this.origin.content() == null ||
			this.origin.content().isEmpty()) {
			throw new PasswordInvalidException();
		}
		return this.origin.content();
	}
}
