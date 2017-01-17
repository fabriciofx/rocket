package com.github.fabriciofx.rocket.db.mysql;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Positive;
import com.github.fabriciofx.rocket.db.Url;

public final class UrlMysql implements Url {
	private final static String DEFAULT_HOST = "localhost";
	private final static int DEFAULT_PORT = 3306;

	private final String host;
	private final int port;
	private final String dbname;

	public UrlMysql(final String dbname) {
		this(DEFAULT_HOST, dbname);
	}

	public UrlMysql(final String host, final String dbname) {
		this(host, DEFAULT_PORT, dbname);
	}

	public UrlMysql(final String host, final int port, final String dbname) {
		this.host = host;
		this.port = port;
		this.dbname = dbname;
	}

	@Override
	public String string() {
		return String.format(
			"jdbc:mysql://%s:%d/%s",
			new NotEmpty<String>(new NotNull<>()).valid(host),
			new Positive<Integer>(new NotNull<>()).valid(port),
			new NotNull<String>().valid(dbname)
		);
	}
}
