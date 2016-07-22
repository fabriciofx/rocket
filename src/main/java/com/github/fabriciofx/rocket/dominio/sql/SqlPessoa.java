package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fones;
import com.github.fabriciofx.rocket.dominio.Pessoa;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoa implements Pessoa {
	private final transient DataSource ds;
	private final transient Id id;
	
	public SqlPessoa(final DataSource ds, final Id id) {
		this.ds = ds;
		this.id = id;
	}
	
	@Override
	public Id id() {
		return id;
	}
	
	@Override
	public Fones fones() {
		return new SqlFones(ds, id);
	}

	@Override
	public Media print(final Media media) throws IOException {
		return fones().print(
			media
			.with("id", id.toString())
			.with("nome", nome())
		);
	}
	
	private String nome() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT nome FROM pessoa WHERE id = ?")
				.set(id)
				.select(new SingleOutcome<String>(String.class));
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}
}
