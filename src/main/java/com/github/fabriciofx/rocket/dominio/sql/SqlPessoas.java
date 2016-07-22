package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fones;
import com.github.fabriciofx.rocket.dominio.Pessoa;
import com.github.fabriciofx.rocket.dominio.Pessoas;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.NumId;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoas implements Pessoas {
	private final transient DataSource ds;

	public SqlPessoas(final DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Pessoa pessoa(final Id id) throws IOException {
		return new SqlPessoa(ds, id);
	}

	@Override
	public Pessoa salva(final String nome, final List<String> fones)
			throws IOException {
		try {
			final Id id = new NumId(
				new JdbcSession(ds).sql("INSERT INTO pessoa (nome) VALUES (?)")
					.set(nome)
					.insert(new SingleOutcome<Long>(Long.class))
			);
			final Fones fs = new SqlFones(ds, id);
			for (final String f : fones) {
				fs.adiciona(f);
			}
			return new SqlPessoa(ds, id);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
