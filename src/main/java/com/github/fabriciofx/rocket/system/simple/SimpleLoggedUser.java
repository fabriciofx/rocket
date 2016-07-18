package com.github.fabriciofx.rocket.system.simple;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.documento.Email;
import com.github.fabriciofx.rocket.security.Password;
import com.github.fabriciofx.rocket.system.LoggedUser;
import com.github.fabriciofx.rocket.system.User;

public class SimpleLoggedUser implements LoggedUser {
	private final transient User origin;
	private final transient boolean logged;

	public SimpleLoggedUser(final User origin) {
		this(origin, true);
	}
	
	public SimpleLoggedUser(final User origin, final boolean logged) {
		this.origin = origin;
		this.logged = logged;
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
	public boolean logged() throws IOException {
		return logged;
	}
}
