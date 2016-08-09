package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlFone implements Fone {
	private final transient DataSource ds;
	private final transient Id id;
	private final transient String numero;

	public SqlFone(final Database db, final Id id, final String numero)
			throws IOException {
		this(db.source(), id, numero);
	}

	public SqlFone(final DataSource ds, final Id id, final String numero) {
		this.ds = ds;
		this.id = id;
		this.numero = numero;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return media
			.with("fone", numero)
			.with("tipo", tipo())
			.with("operadora", operadora());
	}
	
	@Override
	public String numero() throws IOException {
		return numero;
	}

	@Override
	public Tipo tipo() throws IOException {
		try {
			return Tipo.valueOf(
				new JdbcSession(ds)
					.sql("SELECT tipo FROM fone WHERE id = ? "
						+ "AND numero = ?")
					.set(id)
					.set(numero)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Operadora operadora() throws IOException {
		try {
			return Operadora.valueOf(
				new JdbcSession(ds)
					.sql("SELECT operadora FROM fone WHERE id = ? "
						+ "AND numero = ?")
					.set(id)
					.set(numero)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
	
	public void apaga() throws IOException {
		try {
			new JdbcSession(ds)
				.sql("DELETE FROM fone WHERE id = ? AND numero = ?")
				.set(id)
				.set(numero)
				.execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
	
	public void atualiza(final String numero, final Tipo tipo,
			final Operadora operadora) throws IOException {
		try {
			new JdbcSession(ds)
				.sql("UPDATE fone SET numero = ?, tipo = ?, operadora = ? "
					+ "WHERE id = ?")
				.set(numero)
				.set(tipo)
				.set(operadora)
				.set(id)
				.execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
