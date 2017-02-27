package com.github.fabriciofx.rocket.db.test;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.ScriptSql;
import com.github.fabriciofx.rocket.db.Url;
import com.github.fabriciofx.rocket.db.h2.DatabaseH2;
import com.github.fabriciofx.rocket.user.User;

public final class DatabaseTest implements Database {
	private final transient Database db;

	public DatabaseTest(final String dbname) {
		this(memoryH2Database(dbname));
	}
	
	public DatabaseTest(final Database db) {
		this.db = db;
	}

	@Override
	public User user() {
		return db.user();
	}

	@Override
	public Url url() {
		return db.url();
	}

	@Override
	public DataSource source() throws IOException {
		return db.source();
	}

	@Override
	public Database exec(final ScriptSql script) throws IOException {
		script.exec(db);
		return db;
	}
	
	private static Database memoryH2Database(final String dbname) {
		try {
			return new DatabaseH2(dbname, DatabaseH2.Mode.MEMORY);
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
