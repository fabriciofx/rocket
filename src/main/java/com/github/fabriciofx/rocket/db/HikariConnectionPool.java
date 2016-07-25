package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.system.NamedUser;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class HikariConnectionPool implements ConnectionPool {
	private final transient HikariConfig config;

	public HikariConnectionPool(final String url, final NamedUser user)
			throws IOException {
		this(url, user, config(url, user));
	}

	public HikariConnectionPool(final String url, final NamedUser user,
			final HikariConfig config) {
		this.config = config;
	}

	@Override
	public DataSource dataSource() throws IOException {
		return new HikariDataSource(config);
	}

	private static HikariConfig config(final String url, final NamedUser user)
			throws IOException {
		final HikariConfig config = new HikariConfig();
		try {
			config.setJdbcUrl(url);
			config.setUsername(user.name().toString());
			config.setPassword(user.password().toString());
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
		return config;
	}
}
