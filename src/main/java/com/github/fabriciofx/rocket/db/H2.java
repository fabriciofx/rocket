package com.github.fabriciofx.rocket.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Positive;

// H2 support concurrent transactions only in standalone mode, when you connect
// through a TCP connection, but not in embedded mode.
public final class H2 implements Dbms {
	public enum Mode {
		EMBEDDED, MEMORY, TCP;
	}

	private final static String DEFAULT_DRIVER = "org.h2.Driver";
	private final static String DEFAULT_HOST = "localhost";
	private final static int DEFAULT_PORT = 9092;

	private final transient String driver;
	private final transient String host;
	private final transient int port;
	private final transient String base;
	private final transient Mode mode;

	public H2(final String base) {
		this(base, Mode.EMBEDDED);
	}

	public H2(final String base, final Mode mode) {
		this(DEFAULT_HOST, base, mode);
	}

	public H2(final String host, final String base, final Mode mode) {
		this(host, DEFAULT_PORT, base, mode);
	}

	public H2(final String host, final int port, final String base,
			final Mode mode) {
		this(DEFAULT_DRIVER, host, port, base, mode);
	}

	public H2(final String driver, final String host, final int port,
			final String base, final Mode mode) {
		this.driver = new NotEmpty<String>(new NotNull<>())
				.valid(driver);
		this.host = new NotEmpty<String>(new NotNull<>()).valid(host);
		this.port = new Positive<Integer>(new NotNull<>()).valid(port);
		this.base = new NotNull<String>().valid(base);
		this.mode = new NotNull<H2.Mode>().valid(mode);
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
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final String url;
		switch (mode) {
		case EMBEDDED:
			url = String.format("jdbc:h2:%s%s%s", path.toString(),
					File.separator, base);
			break;
		case MEMORY:
			url = String.format("jdbc:h2:mem:", base);
			break;
		case TCP:
			url = String.format("jdbc:h2:tcp://%s:%d//%s/%s", host, port,
					path.toString(), base);
			break;
		default:
			throw new RuntimeException("invalid mode for H2");
		}
		return url;
	}
}
