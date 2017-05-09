package com.github.fabriciofx.rocket.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.password.PasswordPlain;
import com.github.fabriciofx.rocket.user.User;
import com.github.fabriciofx.rocket.user.UserSmart;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class DatabaseH2File implements Database {
	private final String name;
	private final User user;

	public DatabaseH2File(final String name) {
		this(name, new UserSmart("sa", new PasswordPlain("")));
	}
	
	public DatabaseH2File(final String name, final User user) {
		this.name = name;
		this.user = user;
	}

	@Override
	public String url() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		return String.format(
			"jdbc:h2:%s%s%s",
			path.toString(),
			File.separator,
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
