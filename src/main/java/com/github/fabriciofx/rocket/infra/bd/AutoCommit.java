package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class AutoCommit implements Comando {
	private final transient List<Comando> comandos;

	public AutoCommit(final Comando... comandos) {
		this(Arrays.asList(comandos));
	}

	public AutoCommit(final List<Comando> comandos) {
		this.comandos = comandos;
	}

	@Override
	public void execute(final Conexao conexao) throws IOException {
		try {
			for (final Comando comando : comandos) {
				comando.execute(conexao);
				conexao.efetiva();
			}
		} catch (final Exception e) {
			throw new IOException(e);
		}
	}
}
