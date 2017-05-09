package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.ds.DataSourceH2;

public final class DatabaseH2Mem implements Database {
	private final String name;
	
	public DatabaseH2Mem(final String name) {
		this.name = name;
	}

	@Override
	public String url() {
		return String.format(
			"jdbc:h2:mem:%s:DB_CLOSE_DELAY=-1",
			this.name
		);
	}

	@Override
	public DataSource source() throws IOException {
		return new DataSourceH2(this.url());
	}

	@Override
	public Database exec(final ScriptSql script) throws IOException {
		script.exec(this);
		return this;
	}
}
