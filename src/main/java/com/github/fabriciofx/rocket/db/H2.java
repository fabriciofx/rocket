package com.github.fabriciofx.rocket.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

// H2 support concurrent transactions only in standalone mode, when you connect
// through a TCP connection, but not in embedded mode.
public final class H2 implements Sgbd {
	public enum Modo {
		EMBEDDED, MEMORY, TCP;
	}

	private final static String DRIVER_PADRAO = "org.h2.Driver";
	private final static String HOST_PADRAO = "localhost";
	private final static int PORTA_PADRAO = 9092;

	private final transient String driver;
	private final transient String host;
	private final transient int porta;
	private final transient String banco;
	private final transient Modo modo;

	public H2(final String banco) {
		this(banco, Modo.EMBEDDED);
	}

	public H2(final String banco, final Modo modo) {
		this(HOST_PADRAO, banco, modo);
	}

	public H2(final String host, final String banco, final Modo modo) {
		this(host, PORTA_PADRAO, banco, modo);
	}

	public H2(final String host, final int porta, final String banco,
			final Modo modo) {
		this(DRIVER_PADRAO, host, porta, banco, modo);
	}

	public H2(final String driver, final String host, final int porta,
			final String banco, final Modo modo) {
		this.driver = new NotEmpty<String>(new NotNull<>())
				.valid(driver);
		this.host = new NotEmpty<String>(new NotNull<>()).valid(host);
		this.porta = porta;
		this.banco = new NotNull<String>().valid(banco);
		this.modo = new NotNull<H2.Modo>().valid(modo);
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
		switch (modo) {
		case EMBEDDED:
			url = String.format("jdbc:h2:%s%s%s", path.toString(),
					File.separator, banco);
			break;
		case MEMORY:
			url = String.format("jdbc:h2:mem:", banco);
			break;
		case TCP:
			url = String.format("jdbc:h2:tcp://%s:%d//%s/%s", host, porta,
					path.toString(), banco);
			break;
		default:
			throw new RuntimeException("Modo inválido de utilização do H2");
		}
		return url;
	}
}
