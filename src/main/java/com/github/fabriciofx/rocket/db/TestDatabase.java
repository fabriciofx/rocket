package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.system.NamedUser;

public final class TestDatabase implements Database {
	private final transient Database db;

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
	public DataSource dataSource() throws IOException {
		return db.dataSource();
	}

	@Override
	public void exec(final SqlScript script) throws IOException {
		script.exec(db);
	}
}
