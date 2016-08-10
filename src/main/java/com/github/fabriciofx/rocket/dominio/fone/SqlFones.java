package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.dominio.fone.Fone.Operadora;
import com.github.fabriciofx.rocket.dominio.fone.Fone.Tipo;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlFones implements Fones {
	private final transient Database db;
	private final transient Id id;
	
	public SqlFones(final Database db, final Id id) {
		this.db = db;
		this.id = id;
	}

	@Override
	public Media print(final Media media) throws IOException {
		Media m = media;
		for (final Fone f : todos()) {
			m = f.print(m);
		}
		return m;
	}

	@Override
	public List<Fone> todos() throws IOException {
		final List<Fone> todos = new ArrayList<>();
		for (final String numero : numeros()) {
			todos.add(new SqlFone(db, id, numero));
		}
		return todos;
	}
	
	public void adiciona(final String numero, final Tipo tipo,
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
	
	private List<String> numeros() throws IOException {
		try {
			return new JdbcSession(db.source())
				.sql("SELECT numero FROM fone WHERE id = ?")
				.set(id)
				.select(
					new ListOutcome<String>(
						new ListOutcome.Mapping<String>() {
							@Override
							public String map(final ResultSet rs)
								throws SQLException {
								return rs.getString(1);
							}
						}
					)
				);
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}	
}
