package com.github.fabriciofx.rocket.db;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.db.mysql.DatabaseMysql;

public final class TesteMysqlDatabase {
	@Test
	public void url() throws IOException {
		final String name = "testdb";
		final Database mysql = new DatabaseMysql(name);
		assertEquals("jdbc:mysql://localhost:3306/testdb",
				mysql.url().string()
		);
	}
}
