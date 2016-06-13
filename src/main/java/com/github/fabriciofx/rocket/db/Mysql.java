package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Positive;

public final class Mysql implements Dbms {
	private final static String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
	private final static String DEFAULT_HOST = "localhost";
	private final static int DEFAULT_PORT = 3306;

	private final transient String driver;
	private final transient String host;
	private final transient int port;
	private final transient String base;

	public Mysql(final String base) {
		this(DEFAULT_HOST, base);
	}

	public Mysql(final String host, final String base) {
		this(host, DEFAULT_PORT, base);
	}

	public Mysql(final String host, final int port, final String base) {
		this(DEFAULT_DRIVER, host, port, base);
	}

	public Mysql(final String driver, final String host, final int port,
			final String base) {
		this.driver = new NotEmpty<String>(new NotNull<>()).valid(driver);
		this.host = new NotEmpty<String>(new NotNull<>()).valid(host);
		this.port = new Positive<Integer>(new NotNull<>()).valid(port);
		this.base = new NotNull<String>().valid(base);
	}

	@Override
	public void init() throws IOException {
		try {
			Class.forName(driver);
		} catch (final ClassNotFoundException e) {
			throw new IOException(e);
		}
	}

	@Override
	public String url() {
		return String.format("jdbc:mysql://%s:%d/%s", host, port, base);
	}
}
