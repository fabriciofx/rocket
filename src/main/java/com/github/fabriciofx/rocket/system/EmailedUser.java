package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.doc.Email;
import com.github.fabriciofx.rocket.security.Password;

public final class EmailedUser implements User<Email> {
	private final transient Email email;
	private final transient Password password;

	public EmailedUser(final Email email, final Password password) {
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
