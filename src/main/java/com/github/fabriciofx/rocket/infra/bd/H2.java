package com.github.fabriciofx.rocket.infra.bd;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

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
	private final transient Modo modo;

	public H2() {
		this(Modo.EMBEDDED);
	}

	public H2(final Modo modo) {
		this(HOST_PADRAO, modo);
	}

	public H2(final String host, final Modo modo) {
		this(host, PORTA_PADRAO, modo);
	}

	public H2(final String host, final int porta, final Modo modo) {
		this(DRIVER_PADRAO, host, porta, modo);
	}

	public H2(final String driver, final String host, final int porta,
			final Modo modo) {
		this.driver = new RestNaoVazia<String>(new RestNaoNulo<>())
				.valido(driver);
		this.host = new RestNaoVazia<String>(new RestNaoNulo<>()).valido(host);
		this.porta = porta;
		this.modo = new RestNaoNulo<H2.Modo>().valido(modo);
	}

	@Override
	public String driver() {
		return driver;
	}

	@Override
	public String host() {
		return host;
	}

	@Override
	public int porta() {
		return porta;
	}

	@Override
	public String url(final String banco) {
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
