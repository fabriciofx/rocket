package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public final class Mysql implements Sgbd {
	private final static String DRIVER_PADRAO = "com.mysql.jdbc.Driver";
	private final static String HOST_PADRAO = "localhost";
	private final static int PORTA_PADRAO = 3306;

	private final transient String driver;
	private final transient String host;
	private final transient int porta;
	private final transient String banco;

	public Mysql(final String banco) {
		this(HOST_PADRAO, banco);
	}

	public Mysql(final String host, final String banco) {
		this(host, PORTA_PADRAO, banco);
	}

	public Mysql(final String host, final int porta, final String banco) {
		this(DRIVER_PADRAO, host, porta, banco);
	}

	public Mysql(final String driver, final String host, final int porta,
			final String banco) {
		this.driver = new NotEmpty<String>(new NotNull<>())
				.valid(driver);
		this.host = new NotEmpty<String>(new NotNull<>()).valid(host);
		this.porta = porta;
		this.banco = new NotNull<String>().valid(banco);
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
		return String.format("jdbc:mysql://%s:%d/%s", host, porta, banco);
	}
}
