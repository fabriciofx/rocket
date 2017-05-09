package com.github.fabriciofx.rocket.ds;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.h2.Driver;

public final class DataSourceH2 implements DataSource {
	private final Driver driver;
	private final String url;
	
	public DataSourceH2(final String url) {
		this(new org.h2.Driver(), url);
	}
	
	public DataSourceH2(final Driver driver, final String url) {
		this.driver = driver;
		this.url = url;
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		throw new UnsupportedOperationException("#getLogWriter()");
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		throw new UnsupportedOperationException("#setLogWriter()");
	}

	@Override
	public void setLoginTimeout(final int seconds) throws SQLException {
		throw new UnsupportedOperationException("#setLoginTimeout()");
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		throw new UnsupportedOperationException("#getLoginTimeout()");
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException("#getParentLogger()");
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new UnsupportedOperationException("#unwrap()");
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException("#isWrapperFor()");
	}

	@Override
	public Connection getConnection() throws SQLException {
		return this.driver.connect(this.url, new Properties());
	}

	@Override
	public Connection getConnection(String username, String password)
		throws SQLException {
		throw new UnsupportedOperationException("#getConnection()");
	}	
}
