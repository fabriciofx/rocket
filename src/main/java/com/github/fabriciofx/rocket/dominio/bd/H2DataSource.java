package com.github.fabriciofx.rocket.dominio.bd;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class H2DataSource implements DataSource {
	private final transient DataSource ds;

	public H2DataSource() {
		this("testebd");
	}
	
	public H2DataSource(final String database) {
		this(url(database), "sa", "");
	}

	public H2DataSource(final String url, final String username,
			final String password) {
		this(hikari(url, username, password));
	}

	public H2DataSource(final DataSource ds) {
		this.ds = ds;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return ds.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		ds.setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		ds.setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return ds.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return ds.getParentLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return ds.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return ds.isWrapperFor(iface);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return ds.getConnection(username, password);
	}

	private static String url(final String database) {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		return String.format("jdbc:h2:%s%s%s", path.toString(), File.separator,
				database);
	}

	private static DataSource hikari(final String url, final String username,
			final String password) {
		final HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		return new HikariDataSource(config);
	}
}
