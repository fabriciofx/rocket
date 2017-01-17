package com.github.fabriciofx.rocket.db.mysql;

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

public final class DatabaseMysql implements Database {
	private final transient Config config;

	public DatabaseMysql(final String dbname) throws IOException {
		this(
			new Config(
				new UrlMysql(dbname),
					new UserSmart(
						"root",
						new Password(new Plain(), "")
				)
			)
		);
	}

	public DatabaseMysql(final String dbname, final User user)
			throws IOException {
		this(
			new Config(
				new UrlMysql(dbname),
				user
			)
		);
	}

	public DatabaseMysql(final Config config) {
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