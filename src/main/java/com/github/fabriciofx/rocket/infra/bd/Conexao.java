package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Conexao {
	private final transient Connection conn;

	public Conexao(final Sgbd sgbd, final Usuario usuario) throws IOException {
		sgbd.init();
		this.conn = connection(sgbd.url(), usuario);
	}

	public PreparedStatement statement(final String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

	public void autoCommit(final boolean auto) throws SQLException {
		conn.setAutoCommit(auto);
	}

	public void commit() throws SQLException {
		conn.commit();
	}

	public void rollback() throws SQLException {
		conn.rollback();
	}

	public void fecha() throws IOException {
		try {
			if (conn.isClosed()) {
				throw new IllegalStateException(
						"não é possível fechar uma conexão já fechada");
			} else {
				conn.close();
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	private Connection connection(final String url, final Usuario usuario)
			throws IOException {
		try {
			return DriverManager.getConnection(url, usuario.nome(),
					usuario.senha());
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
