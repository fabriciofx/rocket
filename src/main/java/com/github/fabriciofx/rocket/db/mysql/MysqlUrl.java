package com.github.fabriciofx.rocket.db.mysql;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Positive;
import com.github.fabriciofx.rocket.db.Url;

public final class MysqlUrl implements Url {
	private final static String DEFAULT_HOST = "localhost";
	private final static int DEFAULT_PORT = 3306;

	private final transient String host;
	private final transient int port;
	private final transient String dbname;

	public MysqlUrl(final String dbname) {
		this(DEFAULT_HOST, dbname);
	}

	public MysqlUrl(final String host, final String dbname) {
		this(host, DEFAULT_PORT, dbname);
	}

	public MysqlUrl(final String host, final int port, final String dbname) {
		this.host = host;
		this.port = port;
		this.dbname = dbname;
	}

	@Override
	public String string() {
		return String.format("jdbc:mysql://%s:%d/%s",
			new NotEmpty<String>(new NotNull<>()).valid(host),
			new Positive<Integer>(new NotNull<>()).valid(port),
			new NotNull<String>().valid(dbname)
		);
	}
}
