package com.github.fabriciofx.rocket.db;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fabriciofx.rocket.db.Connection;
import com.github.fabriciofx.rocket.db.Data;
import com.github.fabriciofx.rocket.db.H2;
import com.github.fabriciofx.rocket.db.Insert;
import com.github.fabriciofx.rocket.db.Select;
import com.github.fabriciofx.rocket.db.Dbms;
import com.github.fabriciofx.rocket.db.Update;
import com.github.fabriciofx.rocket.db.User;

public final class TesteDbms {
	private final String DB_NAME = "logdb";
	private final transient Dbms dbms = new H2(DB_NAME);
	private Connection connection;

	@Before
	public void init() throws IOException {
		connection = new Connection(dbms, new User("sa", ""));
	}

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
	public void url() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		assertEquals(
			String.format("jdbc:h2:%s%s%s", path, File.separator, DB_NAME),
			dbms.url()
		);
	}

	@Test
	public void create() throws IOException {
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
				.execute(connection);
	}

	@Test
	public void insertOne() throws IOException {
		final long id = new Date().getTime();
		final String msg = "A log message";
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
				.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg)
			.execute(connection);
		final Data logs = new Select("SELECT * FROM log").execute(connection);
		assertEquals(logs.item(0, "id"), id);
		assertEquals(logs.item(0, "info"), msg);
	}

	@Test
	public void insertThree() throws IOException {
		final long id = new Date().getTime();
		final String msg = "A log message";
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
				.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg)
			.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 1, msg + "1")
			.execute(connection);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 2, msg + "2")
			.execute(connection);
		final Select select = new Select("SELECT * FROM log");
		final Data logs = select.execute(connection);
		assertEquals(logs.item(0, "id"), id);
		assertEquals(logs.item(0, "info"), msg);
		assertEquals(logs.item(1, "id"), id + 1);
		assertEquals(logs.item(1, "info"), msg + "1");
		assertEquals(logs.item(2, "id"), id + 2);
		assertEquals(logs.item(2, "info"), msg + "2");
	}
}
