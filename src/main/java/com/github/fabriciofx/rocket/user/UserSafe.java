package com.github.fabriciofx.rocket.user;

import java.io.IOException;
import java.util.Map;

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
			throw new UserNameInvalidException(
				"the name of the user is invalid"
			);
		}
		return this.origin.name();
	}

	@Override
	public String password() throws IOException {
		if (this.origin == null ||
			this.origin.password() == null ||
			this.origin.password().isEmpty()) {
			throw new UserPasswordInvalidException(
				"the password of the user is invalid"
			);
		}
		return this.origin.password();
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
