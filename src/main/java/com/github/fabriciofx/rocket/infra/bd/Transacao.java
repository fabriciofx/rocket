package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public final class Transacao<T> {
	private final transient Conexao conexao;

	public Transacao(final Conexao conexao) {
		this.conexao = conexao;
	}

	public T execute(final Callable<T> callable) throws IOException {
		try {
			inicio();
			T resultado = callable.call();
			commit();
			return resultado;
		} catch (final Exception e) {
			try {
				rollback();
			} catch (final SQLException sqle) {
				throw new IOException(sqle);
			}
			throw new IOException(e);
		}
	}

	private void inicio() throws SQLException {
		conexao.autoCommit(false);
	}

	private void commit() throws SQLException {
		conexao.commit();
		conexao.autoCommit(true);
	}

	private void rollback() throws SQLException {
		conexao.rollback();
	}
}
