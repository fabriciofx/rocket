package com.github.fabriciofx.rocket.db.h2;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.db.Config;
import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.ScriptSql;
import com.github.fabriciofx.rocket.db.Url;
import com.github.fabriciofx.rocket.security.Plain;
import com.github.fabriciofx.rocket.system.Password;
import com.github.fabriciofx.rocket.system.UserSmart;
import com.github.fabriciofx.rocket.system.User;

public final class DatabaseH2 implements Database {
	public enum Mode {
		EMBEDDED, MEMORY, TCP;
	}
	
	private final Config config;

	public DatabaseH2(final String dbname) throws IOException {
		this(dbname, DatabaseH2.Mode.EMBEDDED);
	}

	public DatabaseH2(final String dbname, final DatabaseH2.Mode mode)
			throws IOException {
		this(
			dbname,
			mode,
			new UserSmart(
				"sa",
				new Password(new Plain(), "")
			)
		);
	}

	public DatabaseH2(final String dbname, final DatabaseH2.Mode mode,
			final User user) throws IOException {
		this(new Config(new UrlH2(dbname, mode), user));
	}

	public DatabaseH2(final Config config) {
		this.config = config;
	}

	@Override
	public User user() {
		return config.user();
	}

	@Override
	public Url url() {
		return config.url();
	}

	@Override
	public DataSource source() throws IOException {
		return config.source();
	}

	@Override
	public Database exec(final ScriptSql script) throws IOException {
		script.exec(this);
		return this;
	}
}
