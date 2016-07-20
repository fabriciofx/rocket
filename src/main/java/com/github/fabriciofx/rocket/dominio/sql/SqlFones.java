package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.Fones;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlFones implements Fones {
	private final transient DataSource ds;
	private final transient long pessoaId;
	
	public SqlFones(final DataSource ds, final long pessoaId) {
		this.ds = ds;
		this.pessoaId = pessoaId;
	}

	@Override
	public Fone fone(final long pessoaId) throws IOException {
		return new SqlFone(ds, pessoaId);
	}

	@Override
	public void salva(final String numero) throws IOException {
		try {
			new JdbcSession(ds)
				.sql("INSERT INTO fone (pessoa, numero) VALUES (?, ?)")
				.set(pessoaId)
				.set(numero)
				.insert(SingleOutcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
