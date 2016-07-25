package com.github.fabriciofx.rocket.db;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Positive;

public final class MysqlUrl implements Url {
	private final static String DEFAULT_HOST = "localhost";
	private final static int DEFAULT_PORT = 3306;

	private final transient String host;
	private final transient int port;
	private final transient String database;

	public MysqlUrl(final String database) {
		this(DEFAULT_HOST, database);
	}

	public MysqlUrl(final String host, final String database) {
		this(host, DEFAULT_PORT, database);
	}

	public MysqlUrl(final String host, final int port, final String database) {
		this.host = host;
		this.port = port;
		this.database = database;
	}

	@Override
	public String string() {
		return String.format("jdbc:mysql://%s:%d/%s",
			new NotEmpty<String>(new NotNull<>()).valid(host),
			new Positive<Integer>(new NotNull<>()).valid(port),
			new NotNull<String>().valid(database)
		);
	}
}
