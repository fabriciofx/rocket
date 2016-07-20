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
	public Pessoa salva(final String nome, final String fone)
			throws IOException {
		try {
			final JdbcSession session = new JdbcSession(ds).autocommit(false);
			final long id = session.sql("INSERT INTO pessoa (nome) VALUES (?)")
				.set(nome)
				.insert(new SingleOutcome<Long>(Long.class));
			session.sql("INSERT INTO fone (pessoa, numero) VALUES (?, ?)")
				.set(id)
				.set(fone)
				.insert(SingleOutcome.VOID);
			session.commit();
			return new SqlPessoa(ds, id);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
