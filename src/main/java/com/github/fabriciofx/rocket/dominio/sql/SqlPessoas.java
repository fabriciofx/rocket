package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Pessoa;
import com.github.fabriciofx.rocket.dominio.Pessoas;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoas implements Pessoas {
	private final transient DataSource ds;

	public SqlPessoas(final DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Pessoa pessoa(final long id) throws IOException {
		return new SqlPessoa(ds, id);
	}

	@Override
	public Pessoa salva(final String nome) throws IOException {
		try {
			final long id = new JdbcSession(ds)
				.sql("INSERT INTO pessoa (nome) VALUES (?)")
				.set(nome)
				.insert(new SingleOutcome<Long>(Long.class));
			return new SqlPessoa(ds, id);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
