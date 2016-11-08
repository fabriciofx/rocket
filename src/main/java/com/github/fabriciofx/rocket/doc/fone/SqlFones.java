package com.github.fabriciofx.rocket.doc.fone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.doc.fone.Fone.Operadora;
import com.github.fabriciofx.rocket.doc.fone.Fone.Tipo;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlFones implements Fones {
	private final Database db;
	private final Id id;
	
	public SqlFones(final Database db, final Id id) {
		this.db = db;
		this.id = id;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return new MemFones(todos()).print(media);
	}

	@Override
	public void adicione(final String numero, final Tipo tipo,
			final Operadora operadora) throws IOException {
		try {
			new JdbcSession(db.source())
				.sql("INSERT INTO fone (id, numero, tipo, operadora) "
					+ "VALUES (?, ?, ?, ?)")
				.set(id)
				.set(numero)
				.set(tipo)
				.set(operadora)
				.insert(SingleOutcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<Fone> todos() throws IOException {
		try {
			return new JdbcSession(db.source())
				.sql("SELECT numero, tipo, operadora FROM fone WHERE id = ?")
				.set(id)
				.select(new ListOutcome<Fone>(new SqlFoneMapping()));
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}
}
