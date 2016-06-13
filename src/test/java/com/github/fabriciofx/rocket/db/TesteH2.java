package com.github.fabriciofx.rocket.db;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.Test;

import com.github.fabriciofx.rocket.db.Connection;
import com.github.fabriciofx.rocket.db.H2;
import com.github.fabriciofx.rocket.db.Insert;
import com.github.fabriciofx.rocket.db.Dbms;
import com.github.fabriciofx.rocket.db.Update;
import com.github.fabriciofx.rocket.db.User;
import com.github.fabriciofx.rocket.db.H2.Mode;

public final class TesteH2 {
	private final static transient String DB_NAME = "testdb";
	private Connection connection;

	// @After
	// public void finalize() {
	// try {
	// new Update("DROP TABLE IF EXISTS log").execute(connection);
	// connection.close();
	// } catch (final IOException e) {
	// e.printStackTrace();
	// }
	// }

	@Test
	public void embedded() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final Dbms h2 = new H2(DB_NAME);
		assertEquals(
			String.format("jdbc:h2:%s%s%s", path, File.separator, DB_NAME),
			h2.url()
		);
	}

	@Test
	public void memory() {
		final Dbms h2 = new H2(DB_NAME, Mode.MEMORY);
		assertEquals(String.format("jdbc:h2:mem:", DB_NAME), h2.url());
	}

	@Test
	public void tcp() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final Dbms h2 = new H2(DB_NAME, Mode.TCP);
		assertEquals(String.format("jdbc:h2:tcp://localhost:9092//%s/%s",
				path.toString(), DB_NAME), h2.url());
	}

	@Test
	public void servidor() throws IOException, InterruptedException {
		final H2Server server = new H2Server();
		server.start();

		final Dbms h2 = new H2(DB_NAME, Mode.TCP);
		connection = new Connection(h2, new User("sa", ""));

		final long id = new Date().getTime();
		final String msg = "A log menssage";

		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
						.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg)
				.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 1, msg + "1")
				.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 2, msg + "2")
				.execute(connection);
		server.stop();
	}
}
