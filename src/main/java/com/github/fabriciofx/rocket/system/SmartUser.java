package com.github.fabriciofx.rocket.system;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Email;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class SmartUser implements User {
	private final String name;
	private final Password password;
	private final Map<String, String> properties;

	public SmartUser(final Email email, final Password password) {
		this(email, password, Collections.emptyMap());
	}

	public SmartUser(final String name, final Password password) {
		this(name, password, Collections.emptyMap());
	}

	public SmartUser(final Email email, final Password password,
			final Map<String, String> properties) {
		this(email.toString(), password, properties);
	}

	public SmartUser(final String name, final Password password,
			final Map<String, String> properties) {
		this.name = name;
		this.password = password;
		this.properties = Collections.unmodifiableMap(properties);
	}

	@Override
	public String name() throws IOException {
		return name;
	}

	@Override
	public String password() throws IOException {
		return password.toString();
	}

	@Override
	public Map<String, String> properties() throws IOException {
		return properties;
	}

	@Override
	public String toString() {
		return name;
	}
}
