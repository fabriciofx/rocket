package com.github.fabriciofx.rocket.db;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.fabriciofx.rocket.db.Connection;
import com.github.fabriciofx.rocket.db.Data;
import com.github.fabriciofx.rocket.db.H2;
import com.github.fabriciofx.rocket.db.Insert;
import com.github.fabriciofx.rocket.db.Select;
import com.github.fabriciofx.rocket.db.Transaction;
import com.github.fabriciofx.rocket.db.Update;
import com.github.fabriciofx.rocket.db.User;

public final class TesteTransaction {
	private Connection connection;
	private final static transient String DB_NAME = "logdb";
	private final long id = new Date().getTime();
	private final String msg = "A log message";

	private void data(final Connection connection) throws IOException {
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
						.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg)
				.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 1, msg + "1")
				.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 2, msg + "2")
				.execute(connection);
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@After
	public void fini() {
		try {
			new Update("DROP TABLE IF EXISTS log").execute(connection);
			connection.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void success() throws IOException {
		connection = new Connection(new H2(DB_NAME), new User("sa", ""));
		final Transaction<Integer> transaction = new Transaction<>(connection);
		transaction.execute(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				data(connection);
				return 1;
			}
		});
		final Data data = new Select("SELECT * FROM log").execute(connection);
		assertEquals(data.item(0, "id"), id);
		assertEquals(data.item(0, "info"), msg);
		assertEquals(data.item(1, "id"), id + 1);
		assertEquals(data.item(1, "info"), msg + "1");
		assertEquals(data.item(2, "id"), id + 2);
		assertEquals(data.item(2, "info"), msg + "2");
	}

	@Test
	public void fail() {
		try {
			connection = new Connection(new H2(DB_NAME), new User("sa", ""));
			final Transaction<Integer> transaction = new Transaction<>(connection);
			transaction.execute(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					data(connection);
					throw new IOException("intentional transaction fail");
				}
			});
		} catch (final IOException e) {
			try {
				final Data data = new Select("SELECT * FROM log")
					.execute(connection);
				assertEquals(0, data.itens().size());
			} catch (final IOException e2) {
			}
		}
	}
}
