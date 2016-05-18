package com.github.fabriciofx.rocket.infra.bd;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class MySQL implements Sgbd {
	private final static String DRIVER_PADRAO = "com.mysql.jdbc.Driver";
	private final static String HOST_PADRAO = "localhost";
	private final static int PORTA_PADRAO = 3306;

	private final transient String driver;
	private final transient String host;
	private final transient int porta;

	public MySQL() {
		this(HOST_PADRAO);
	}

	public MySQL(final String host) {
		this(host, PORTA_PADRAO);
	}

	public MySQL(final String host, final int porta) {
		this(DRIVER_PADRAO, host, porta);
	}

	public MySQL(final String driver, final String host, final int porta) {
		this.driver = new RestNaoVazia<String>(new RestNaoNulo<>())
				.valido(driver);
		this.host = new RestNaoVazia<String>(new RestNaoNulo<>()).valido(host);
		this.porta = porta;
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
		return String.format("jdbc:mysql://%s:%d/%s", host, porta, banco);
	}
}
