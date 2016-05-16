package com.github.fabriciofx.rocket.infra.bd;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class H2 implements Sgbd {
	private final transient String driver;
	private final transient int porta;

	public H2() {
		this("org.h2.Driver", 5432);
	}

	public H2(final String driver, final int porta) {
		this.driver = driver;
		this.porta = porta;
	}

	@Override
	public String driver() {
		return driver;
	}

	@Override
	public int porta() {
		return porta;
	}

	@Override
	public String url(final String banco) {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		return String.format("jdbc:h2:%s%s%s", path.toString(), File.separator,
				banco);
	}
}
