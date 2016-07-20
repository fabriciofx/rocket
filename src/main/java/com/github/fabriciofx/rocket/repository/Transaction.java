package com.github.fabriciofx.rocket.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import javax.sql.DataSource;

import com.jcabi.jdbc.JdbcSession;

public final class Transaction {
	private final transient DataSource ds;

	public Transaction(final DataSource ds) {
		this.ds = ds;
	}

	public <T> T call(final Callable<T> callable) throws IOException {
		final JdbcSession session = new JdbcSession(ds);
		try {
			session.autocommit(false);
			session.sql("BEGIN TRANSACTION").execute();
			T result = callable.call();
			session.commit();
			return result;
		} catch (final Exception e) {
			try {
				session.sql("ROLLBACK").execute();
			} catch (final SQLException sqle) {
				throw new IOException(sqle);
			}
			throw new IOException(e);
		}
	}
}
