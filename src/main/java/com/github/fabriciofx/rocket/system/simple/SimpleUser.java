package com.github.fabriciofx.rocket.system.simple;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.documento.Email;
import com.github.fabriciofx.rocket.security.Password;
import com.github.fabriciofx.rocket.system.User;

public final class SimpleUser implements User {
	private final transient Email email;
	private final transient Password password;

	public SimpleUser(final Email email, final Password password) {
		this.email = new NotNull<Email>().valid(email);
		this.password = new NotNull<Password>().valid(password);
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
