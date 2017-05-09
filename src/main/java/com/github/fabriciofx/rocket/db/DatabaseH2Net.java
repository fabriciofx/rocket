package com.github.fabriciofx.rocket.db;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.password.PasswordPlain;
import com.github.fabriciofx.rocket.user.User;
import com.github.fabriciofx.rocket.user.UserSmart;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class DatabaseH2Net implements Database {
	private final String host;
	private final int port;
	private final String name;
	private final User user;

	public DatabaseH2Net(final String name) {
		this("localhost", 9092, name, new UserSmart("sa", new PasswordPlain("")));
	}
	
	public DatabaseH2Net(final String name, final User user) {
		this("localhost", 9092, name, user);
	}
	
	public DatabaseH2Net(final String host, final int port, final String name,
		final User user) {
		this.host = host;
		this.port = port;
		this.name = name;
		this.user = user;
	}

	@Override
	public String url() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		return String.format(
			"jdbc:h2:tcp://%s:%d//%s/%s",
			this.host,
			this.port,
			path.toString(),
			this.name
		);
	}

	@Override
	public DataSource source() throws IOException {
		final HikariConfig config = new HikariConfig();
		config.setJdbcUrl(this.url());
		config.setUsername(this.user.name());
		config.setPassword(this.user.password());
		return new HikariDataSource(config);
	}

	@Override
	public Database exec(final ScriptSql script) throws IOException {
		script.exec(this);
		return this;
	}
}
