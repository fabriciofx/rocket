package com.github.fabriciofx.rocket.db.test;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.SqlScript;
import com.github.fabriciofx.rocket.db.Url;
import com.github.fabriciofx.rocket.db.h2.H2Database;
import com.github.fabriciofx.rocket.system.NamedUser;

public final class TestDatabase implements Database {
	private final transient Database db;

	public TestDatabase(final String dbname) {
		this(memoryH2Database(dbname));
	}
	
	public TestDatabase(final Database db) {
		this.db = db;
	}

	@Override
	public NamedUser user() {
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
	public void exec(final SqlScript script) throws IOException {
		script.exec(db);
	}
	
	private static Database memoryH2Database(final String dbname) {
		try {
			return new H2Database(dbname, H2Database.Mode.MEMORY);
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
