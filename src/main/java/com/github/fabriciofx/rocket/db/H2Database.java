package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.security.Plain;
import com.github.fabriciofx.rocket.system.DefaultNamedUser;
import com.github.fabriciofx.rocket.system.NamedUser;
import com.github.fabriciofx.rocket.system.Password;

public final class H2Database implements Database {
	public enum Mode {
		EMBEDDED, MEMORY, TCP;
	}
	
	private final transient Base base;

	public H2Database(final String database) throws IOException {
		this(database, H2Database.Mode.EMBEDDED);
	}

	public H2Database(final String database, final H2Database.Mode mode)
			throws IOException {
		this(
			database,
			mode,
			new DefaultNamedUser(
				new Nome("sa"),
				new Password(new Plain(), "")
			)
		);
	}

	public H2Database(final String database, final H2Database.Mode mode,
			final NamedUser user) throws IOException {
		this(new Base(new H2Url(database, mode), user));
	}

	public H2Database(final Base base) {
		this.base = base;
	}

	@Override
	public NamedUser user() {
		return base.user();
	}

	@Override
	public Url url() {
		return base.url();
	}

	@Override
	public DataSource dataSource() throws IOException {
		return base.dataSource();
	}

	@Override
	public void init() throws IOException {
	}

	@Override
	public void destroy() throws IOException {
	}
}
