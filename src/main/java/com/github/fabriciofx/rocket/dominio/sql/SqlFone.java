package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public class SqlFone implements Fone {
	private final transient DataSource ds;
	private final transient Id id;
	private final transient String numero;
	
	public SqlFone(final DataSource ds, final Id id, final String numero) {
		this.ds = ds;
		this.id = id;
		this.numero = numero;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return media.with("fone", numero());
	}
	
	@Override
	public void salva() throws IOException {
		try {
			new JdbcSession(ds)
				.sql("INSERT INTO fone (pessoa, numero) VALUES (?, ?)")
				.set(id)
				.set(numero)
				.insert(SingleOutcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void apaga() throws IOException {
		try {
			new JdbcSession(ds)
				.sql("DELETE FROM fone WHERE pessoa = ? AND numero = ?")
				.set(id)
				.set(numero)
				.execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
	
	@Override
	public String numero() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT numero FROM fone WHERE pessoa = ?")
				.set(id)
				.select(new SingleOutcome<String>(String.class));
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
