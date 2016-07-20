package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public class SqlFone implements Fone {
	private final transient DataSource ds;
	private final transient long pessoaId;
	
	public SqlFone(final DataSource ds, final long pessoaId) {
		this.ds = ds;
		this.pessoaId = pessoaId;
	}

	@Override
	public Media print(final Media media) throws IOException {
		try {
			final String numero = new JdbcSession(ds)
				.sql("SELECT numero FROM fone WHERE pessoa = ?")
				.set(pessoaId)
				.select(new SingleOutcome<String>(String.class));
			return media.with("pessoa", pessoaId + "").with("numero", numero);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void apaga() throws IOException {
	}
}
