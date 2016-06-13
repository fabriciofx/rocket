package com.github.fabriciofx.rocket.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public final class Transaction<T> {
	private final transient Connection connection;

	public Transaction(final Connection connection) {
		this.connection = connection;
	}

	public T execute(final Callable<T> callable) throws IOException {
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

	private void start() throws SQLException {
		connection.autoCommit(false);
	}

	private void commit() throws SQLException {
		connection.commit();
		connection.autoCommit(true);
	}

	private void rollback() throws SQLException {
		connection.rollback();
		connection.autoCommit(true);
	}
}
