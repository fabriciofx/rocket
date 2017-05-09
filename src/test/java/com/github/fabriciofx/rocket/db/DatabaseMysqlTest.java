package com.github.fabriciofx.rocket.db;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public final class DatabaseMysqlTest {
	@Test
	public void url() throws IOException {
		final Database mysql = new DatabaseMysql("testdb");
		assertEquals(
			"jdbc:mysql://localhost:3306/testdb",
			mysql.url()
		);
	}
}
