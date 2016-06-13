package com.github.fabriciofx.rocket.db;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class Connection {
	private final transient java.sql.Connection conn;

	public Connection(final Dbms dbms, final User user) throws IOException {
		dbms.init();
		this.conn = connection(dbms.url(), user);
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

	public void close() throws IOException {
		try {
			if (conn.isClosed()) {
				throw new IllegalStateException(
					"it's not possible close a connection already closed"
				);
			} else {
				conn.close();
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	private java.sql.Connection connection(final String url, final User user)
		throws IOException {
		try {
			return DriverManager.getConnection(url, user.name(),
					user.password());
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
