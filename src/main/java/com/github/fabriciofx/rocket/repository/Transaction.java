package com.github.fabriciofx.rocket.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import javax.sql.DataSource;

import com.jcabi.jdbc.JdbcSession;

public final class Transaction {
	private final transient JdbcSession session;

	public Transaction(final DataSource ds) {
		this.session = new JdbcSession(ds);
	}

	public <T> T execute(final Callable<T> callable) throws IOException {
		try {
			start();
			T result = callable.call();
			commit();
			return result;
		} catch (final Exception e) {
			try {
				rollback();
			} catch (final SQLException sqle) {
				throw new IOException(sqle);
			}
			throw new IOException(e);
		}
	}

	public JdbcSession session() {
		return session;
	}
	
	private void start() throws SQLException {
		session.autocommit(false);
		session.sql("BEGIN TRANSACTION").execute();
	}

	private void commit() throws SQLException {
		session.commit();
	}

	private void rollback() throws SQLException {
		session.sql("ROLLBACK").execute();
	}
}
