package com.github.fabriciofx.rocket.db;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.github.fabriciofx.rocket.db.Connection;
import com.github.fabriciofx.rocket.db.Data;
import com.github.fabriciofx.rocket.db.Insert;
import com.github.fabriciofx.rocket.db.Mysql;
import com.github.fabriciofx.rocket.db.Select;
import com.github.fabriciofx.rocket.db.Dbms;
import com.github.fabriciofx.rocket.db.Update;
import com.github.fabriciofx.rocket.db.User;

public final class TesteMysql {
	private final static transient String DB_NAME = "testdb";

	@Test
	public void url() {
		final Dbms mysql = new Mysql(DB_NAME);
		assertEquals("jdbc:mysql://localhost:3306/testdb", mysql.url());
	}

	@Test
	public void server() throws IOException, InterruptedException {
		final Dbms mysql = new Mysql(DB_NAME);
		createDB();
		final Connection connection = new Connection(mysql,
			new User("root", "admin")
		);
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
		eraseDB(mysql);
		connection.close();
	}

	private void eraseDB(final Dbms mysql) throws IOException {
		final Connection connection = new Connection(mysql,
			new User("root", "admin")
		);
		new Update("DROP DATABASE IF EXISTS testdb").execute(connection);
		connection.close();
	}

	private void createDB() throws IOException {
		final Dbms mysql = new Mysql("");
		final Connection connection = new Connection(mysql,
			new User("root", "admin")
		);
		new Update("CREATE DATABASE IF NOT EXISTS testdb").execute(connection);
		connection.close();
	}
}
