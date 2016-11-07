package com.github.fabriciofx.rocket.db.mysql;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.db.Config;
import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.SqlScript;
import com.github.fabriciofx.rocket.db.Url;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.system.DefaultNamedUser;
import com.github.fabriciofx.rocket.system.NamedUser;
import com.github.fabriciofx.rocket.system.Password;

public final class MysqlDatabase implements Database {
	private final transient Config config;

	public MysqlDatabase(final String dbname) throws IOException {
		this(
			new Config(
				new MysqlUrl(dbname),
					new DefaultNamedUser(
						new Nome("root"),
						new Password("")
				)
			)
		);
	}

	public MysqlDatabase(final String dbname, final NamedUser user)
			throws IOException {
		this(
			new Config(
				new MysqlUrl(dbname),
				user
			)
		);
	}

	public MysqlDatabase(final Config config) {
		this.config = config;
	}

	@Override
	public NamedUser user() {
		return config.user();
	}

	@Override
	public Url url() {
		return config.url();
	}

	@Override
	public DataSource source() throws IOException {
		return config.source();
	}

	@Override
	public void exec(final SqlScript script) throws IOException {
		script.exec(this);
	}
}
