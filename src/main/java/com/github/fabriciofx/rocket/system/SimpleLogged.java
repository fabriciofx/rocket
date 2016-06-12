package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Email;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.security.Password;

public class SimpleLogged implements Logged {
	private final transient User origin;
	private final transient boolean logged;

	public SimpleLogged(final User origin) {
		this(origin, true);
	}
	
	public SimpleLogged(final User origin, final boolean logado) {
		this.origin = origin;
		this.logged = logado;
	}

	@Override
	public Nome name() throws IOException {
		return origin.name();
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
