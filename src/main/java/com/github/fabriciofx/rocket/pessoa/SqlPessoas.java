package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.sql.SQLException;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.NumId;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoas implements Pessoas {
	private final Database db;

	public SqlPessoas(final Database db) {
		this.db = db;
	}
	
	@Override
	public Pessoa pessoa(final Nome nome, final String endereco,
		final String fone) throws IOException {
		try {
			final Id id = new NumId(
				new JdbcSession(db.source())
					.sql("INSERT INTO pessoa (nome, endereco, fone) " +
						 "VALUES (?, ?, ?)")
					.set(nome)
					.set(endereco)
					.set(fone)
					.insert(SingleOutcome.LAST_INSERT_ID)
				);
			return new SqlPessoa(id, db);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public Pessoa pessoa(final Id id) {
		return new SqlPessoa(id, db);
	}
}
