package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.password.PasswordPlain;
import com.github.fabriciofx.rocket.user.User;
import com.github.fabriciofx.rocket.user.UserSmart;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class DatabaseMysql implements Database {
	private final String host;
	private final int port;
	private final String name;
	private final User user;

	public DatabaseMysql(final String name) {
		this(name, new UserSmart("root", new PasswordPlain("")));
	}
	
	public DatabaseMysql(final String name, final User user) {
		this("localhost", 3306, name, user);
	}
	
	public DatabaseMysql(final String host, final int port, final String name,
		final User user) {
		this.host = host;
		this.port = port;
		this.name = name;
		this.user = user;
	}
	
	@Override
	public String url() {
		return String.format(
			"jdbc:mysql://%s:%d/%s",
			this.host,
			this.port,
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
