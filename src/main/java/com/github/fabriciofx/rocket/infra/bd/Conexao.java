package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Conexao {
	private final transient Connection conn;
	private final transient String url;

	public Conexao(final Sgbd sgbd, final String banco, final Usuario usuario)
			throws IOException {
		try {
			Class.forName(sgbd.driver());
			this.url = sgbd.url(banco);
			this.conn = connection(this.url, usuario);
		} catch (final ClassNotFoundException e) {
			throw new IOException(e);
		}
	}

	public PreparedStatement statement(final String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

	public void efetiva() throws SQLException {
		conn.commit();
	}

	public String url() {
		return url;
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
