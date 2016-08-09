package com.github.fabriciofx.rocket.db;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Positive;

// H2 support concurrent transactions only in standalone mode, when you connect
// through a TCP connection, but not in embedded mode.
public final class H2Url implements Url {
	private final static String DEFAULT_HOST = "localhost";
	private final static int DEFAULT_PORT = 9092;

	private final transient String host;
	private final transient int port;
	private final transient H2Database.Mode mode;
	private final transient String database;

	public H2Url(final String database) {
		this(database, H2Database.Mode.EMBEDDED);
	}

	public H2Url(final String database, final H2Database.Mode mode) {
		this(DEFAULT_HOST, database, mode);
	}

	public H2Url(final String host, final String database,
			final H2Database.Mode mode) {
		this(host, DEFAULT_PORT, database, mode);
	}

	public H2Url(final String host, final int port, final String database,
			final H2Database.Mode mode) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.mode = mode;
	}

	@Override
	public String string() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final String url;
		switch (new NotNull<H2Database.Mode>().valid(mode)) {
		case EMBEDDED:
			url = String.format("jdbc:h2:%s%s%s", path.toString(),
					File.separator, new NotNull<String>().valid(database));
			break;
		case MEMORY:
			url = String.format("jdbc:h2:mem:DB_CLOSE_DELAY=-1",
					new NotNull<String>().valid(database));
			break;
		case TCP:
			url = String.format("jdbc:h2:tcp://%s:%d//%s/%s",
					new NotEmpty<String>(new NotNull<>()).valid(host),
					new Positive<Integer>(new NotNull<>()).valid(port),
					path.toString(), new NotNull<String>().valid(database));
			break;
		default:
			throw new RuntimeException("invalid mode for H2");
		}
		return url;
	}
}
