package com.github.fabriciofx.rocket.db.h2;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.db.Config;
import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.SqlScript;
import com.github.fabriciofx.rocket.db.Url;
import com.github.fabriciofx.rocket.security.Plain;
import com.github.fabriciofx.rocket.system.Password;
import com.github.fabriciofx.rocket.system.SmartUser;
import com.github.fabriciofx.rocket.system.User;

public final class H2Database implements Database {
	public enum Mode {
		EMBEDDED, MEMORY, TCP;
	}
	
	private final transient Config config;

	public H2Database(final String dbname) throws IOException {
		this(dbname, H2Database.Mode.EMBEDDED);
	}

	public H2Database(final String dbname, final H2Database.Mode mode)
			throws IOException {
		this(
			dbname,
			mode,
			new SmartUser(
				"sa",
				new Password(new Plain(), "")
			)
		);
	}

	public H2Database(final String dbname, final H2Database.Mode mode,
			final User user) throws IOException {
		this(new Config(new H2Url(dbname, mode), user));
	}

	public H2Database(final Config config) {
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
	public void exec(final SqlScript script) throws IOException {
		script.exec(this);
	}
}
