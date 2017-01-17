package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.system.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class ConnectionPoolHikari implements ConnectionPool {
	private final HikariConfig config;

	public ConnectionPoolHikari(final Url url, final User user)
			throws IOException {
		this(url, user, config(url, user));
	}

	public ConnectionPoolHikari(final Url url, final User user,
		final HikariConfig config) {
		this.config = config;
	}

	@Override
	public DataSource source() throws IOException {
		return new HikariDataSource(config);
	}

	private static HikariConfig config(final Url url, final User user)
			throws IOException {
		final HikariConfig config = new HikariConfig();
		try {
			config.setJdbcUrl(url.string());
			config.setUsername(user.name().toString());
			config.setPassword(user.password().toString());
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
		return config;
	}
}
