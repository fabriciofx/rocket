package com.github.fabriciofx.rocket.system.simple;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.doc.Email;
import com.github.fabriciofx.rocket.dominio.doc.Nome;
import com.github.fabriciofx.rocket.security.Password;
import com.github.fabriciofx.rocket.system.NamedUser;
import com.github.fabriciofx.rocket.system.User;

public final class SimpleNamedUser implements NamedUser {
	private final transient User origin;
	private final transient Nome name;

	public SimpleNamedUser(final User origin, final Nome name) {
		this.origin = new NotNull<User>().valid(origin);
		this.name = new NotNull<Nome>().valid(name);
	}

	@Override
	public Email email() throws IOException {
		return origin.email();
	}

	@Override
	public Password password() throws IOException {
		return origin.password();
	}

	@Override
	public Nome name() throws IOException {
		return name;
	}
}
