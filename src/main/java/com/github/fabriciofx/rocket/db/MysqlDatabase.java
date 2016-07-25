package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.security.Password;
import com.github.fabriciofx.rocket.system.NamedUser;

public final class MysqlDatabase implements Database {
	private final transient Base base;

	public MysqlDatabase(final String database) throws IOException {
		this(
			new Base(
				new MysqlUrl(database),
					new NamedUser(
						new Nome("root"),
						new Password("")
				)
			)
		);
	}

	public MysqlDatabase(final String database, final NamedUser user)
			throws IOException {
		this(
			new Base(
				new H2Url(database),
				user
			)
		);
	}

	public MysqlDatabase(final Base db) {
		this.base = db;
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
}
