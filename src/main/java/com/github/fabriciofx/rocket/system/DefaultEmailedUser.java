package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Email;

public final class DefaultEmailedUser implements EmailedUser {
	private final transient Email email;
	private final transient Password password;

	public DefaultEmailedUser(final Email email, final Password password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public Email name() throws IOException {
		return new NotNull<Email>().valid(email);
	}

	@Override
	public Password password() throws IOException {
		return new NotNull<Password>().valid(password);
	}
}
