package com.github.fabriciofx.rocket.user;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.github.fabriciofx.rocket.email.Email;
import com.github.fabriciofx.rocket.password.Password;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class UserSmart implements User {
	private final String name;
	private final Password password;
	private final Map<String, String> properties;

	public UserSmart(final Email email, final Password password) {
		this(email, password, Collections.emptyMap());
	}

	public UserSmart(final String name, final Password password) {
		this(name, password, Collections.emptyMap());
	}

	public UserSmart(final Email email, final Password password,
			final Map<String, String> properties) {
		this(email.address(), password, properties);
	}

	public UserSmart(final String name, final Password password,
			final Map<String, String> properties) {
		this.name = name;
		this.password = password;
		this.properties = Collections.unmodifiableMap(properties);
	}

	@Override
	public String name() throws IOException {
		return this.name;
	}

	@Override
	public String password() throws IOException {
		return this.password.content();
	}

	@Override
	public Map<String, String> properties() throws IOException {
		return this.properties;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
