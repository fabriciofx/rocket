package com.github.fabriciofx.rocket.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import com.jcabi.jdbc.JdbcSession;

public final class Transaction {
	private final transient Database db;

	public Transaction(final Database db) {
		this.db = db;
	}

	public <T> T call(final Callable<T> callable) throws IOException {
		final JdbcSession session = new JdbcSession(db.dataSource());
		try {
			session.autocommit(false);
			session.sql("BEGIN TRANSACTION").execute();
			final T result = callable.call();
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
