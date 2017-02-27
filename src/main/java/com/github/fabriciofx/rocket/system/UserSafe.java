package com.github.fabriciofx.rocket.system;

import java.io.IOException;
import java.util.Map;

public final class UserSafe implements User {
	private final User origin;
	
	public UserSafe(final User origin) {
		this.origin = origin;
	}
	
	@Override
	public String name() throws IOException {
		final String name = this.origin.name();
		if (name == null || name.isEmpty()) {
			throw new UserNameInvalidException(name);
		}
		return name;
	}

	@Override
	public String password() throws IOException {
		final String password = this.origin.password();
		if (password == null || password.isEmpty()) {
			throw new UserPasswordInvalidException(password);
		}
		return password;
	}

	@Override
	public Map<String, String> properties() throws IOException {
		final Map<String, String> properties = this.origin.properties();
		if (properties == null) {
			throw new UserPropertiesInvalidException(this.name());
		}
		return properties;
	}
}
