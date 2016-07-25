package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.Nome;

public final class DefaultNamedUser implements NamedUser {
	private final transient Nome nome;
	private final transient Password password;

	public DefaultNamedUser(final Nome nome, final Password password) {
		this.nome = nome;
		this.password = password;
	}

	@Override
	public Nome name() throws IOException {
		return new NotNull<Nome>().valid(nome);
	}

	@Override
	public Password password() throws IOException {
		return new NotNull<Password>().valid(password);
	}
}
