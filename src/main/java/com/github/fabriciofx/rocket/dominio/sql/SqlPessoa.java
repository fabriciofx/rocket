package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fones;
import com.github.fabriciofx.rocket.dominio.Pessoa;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoa implements Pessoa {
	private final transient DataSource ds;
	private final transient long id;
	
	public SqlPessoa(final DataSource ds, final long id) {
		this.ds = ds;
		this.id = id;
	}
	
	@Override
	public Fones fones() {
		return new SqlFones(ds, id);
	}

	@Override
	public Media print(final Media media) throws IOException {
		try {
			final String nome = new JdbcSession(ds)
				.sql("SELECT nome FROM pessoa WHERE id = ?")
				.set(id)
				.select(new SingleOutcome<String>(String.class));
			return media.with("id", id + "").with("nome", nome);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
