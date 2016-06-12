package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.Email;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.security.Password;

public final class DefaultUser implements User {
	private final transient Nome name;
	private final transient Email email;
	private final transient Password password;

	public DefaultUser(final Nome name, final Email email,
			final Password password) {
		this.name = new NotNull<Nome>().valid(name);
		this.email = new NotNull<Email>().valid(email);
		this.password = new NotNull<Password>().valid(password);
	}

	@Override
	public Nome name() throws IOException {
		return name;
	}

	@Override
	public Email email() throws IOException {
		return email;
	}

	@Override
	public Password password() throws IOException {
		return password;
	}
}
