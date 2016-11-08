package com.github.fabriciofx.rocket.doc.fone;

import java.io.IOException;
import java.sql.SQLException;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlFone implements Fone {
	private final Database db;
	private final Id id;
	private final String numero;

	public SqlFone(final Database db, final Id id, final String numero)
			throws IOException {
		this.db = db;
		this.id = id;
		this.numero = numero;
	}

	@Override
	public Media print(final Media media) throws IOException {
		try {
			final Fone fone = new JdbcSession(db.source())
				.sql("SELECT numero, tipo, operadora FROM fone WHERE id = ? "
					+ "AND numero = ?")
				.set(id)
				.set(numero)
				.select(new ListOutcome<Fone>(new SqlFoneMapping()))
				.get(0);
			return fone.print(media);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void apague() throws IOException {
		try {
			new JdbcSession(db.source())
				.sql("DELETE FROM fone WHERE id = ? AND numero = ?")
				.set(id)
				.set(numero)
				.execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
	
	@Override
	public void atualize(final String numero, final Tipo tipo,
			final Operadora operadora) throws IOException {
		try {
			new JdbcSession(db.source())
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
