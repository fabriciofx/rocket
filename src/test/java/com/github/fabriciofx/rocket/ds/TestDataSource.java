package com.github.fabriciofx.rocket.ds;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class TestDataSource {
	private final transient String database;

	public TestDataSource(final String table) {
		this.database = table;
	}

	public DataSource dataSource() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final String url = String.format("jdbc:h2:%s%s%s", path.toString(),
				File.separator, database);
		final HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername("sa");
		config.setPassword("");
		return new HikariDataSource(config);
	}

}
