package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;
import java.util.concurrent.Callable;

public final class Transacao<T> {
	private final transient Conexao conexao;

	public Transacao(final Conexao conexao) {
		this.conexao = conexao;
	}

	private void inicio() throws IOException {
		new Update("BEGIN TRANSACTION").execute(conexao);
	}

	private void commit() throws IOException {
		new Update("COMMIT").execute(conexao);
	}
	
	private void rollback() throws IOException {
		new Update("ROLLBACK").execute(conexao);
	}

	public T execute(final Callable<T> callable) throws IOException {
		try {
			inicio();
			T resultado = callable.call();
			commit();
			return resultado;
		} catch (final Exception e) {
			rollback();
			throw new IOException(e);
		}
	}
}
