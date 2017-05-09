package com.github.fabriciofx.rocket.user;

import java.io.IOException;
import java.util.Map;

import com.github.fabriciofx.rocket.password.ExceptionInvalidPassword;

public final class UserSafe implements User {
	private final User origin;
	
	public UserSafe(final User origin) {
		this.origin = origin;
	}
	
	@Override
	public String name() throws IOException {
		if (this.origin == null ||
			this.origin.name() == null ||
			this.origin.name().isEmpty()) {
			throw new ExceptionInvalidUserName();
		}
		return this.origin.name();
	}

	@Override
	public String password() throws IOException {
		if (this.origin == null ||
			this.origin.password() == null ||
			this.origin.password().isEmpty()) {
			throw new ExceptionInvalidPassword();
		}
		return this.origin.password();
	}

	@Override
	public Map<String, String> properties() throws IOException {
		final Map<String, String> properties = this.origin.properties();
		if (properties == null) {
			throw new ExceptionInvalidUserProperties(this.name());
		}
		return properties;
	}
}
