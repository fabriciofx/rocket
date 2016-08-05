package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.SqlScript;
import com.github.fabriciofx.rocket.db.Url;
import com.github.fabriciofx.rocket.system.NamedUser;

public final class TestePessoaDatabase implements Database {
	private final transient Database db;

	public TestePessoaDatabase(final Database db) {
		this.db = db;
	}

	@Override
	public NamedUser user() {
		return db.user();
	}

	@Override
	public Url url() {
		return db.url();
	}

	@Override
	public DataSource dataSource() throws IOException {
		return db.dataSource();
	}

	@Override
	public void init() throws IOException {
		final URL initUrl = TestePessoaDatabase.class
				.getClassLoader()
				.getResource(
					String.format(
						"%s%s%s",
						"db",
						File.separator,
						"teste-pessoa-db-init.sql"
					)
				);
		try {
			new SqlScript(db).exec(new File(initUrl.toURI()));
		} catch (final URISyntaxException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void destroy() throws IOException {
		final URL destroyUrl = TestePessoaDatabase.class
				.getClassLoader()
				.getResource(
					String.format(
						"%s%s%s",
						"db",
						File.separator,
						"teste-pessoa-db-destroy.sql"
					)
				);
		try {
			new SqlScript(db).exec(new File(destroyUrl.toURI()));
		} catch (final URISyntaxException e) {
			throw new IOException(e);
		}
	}
}
