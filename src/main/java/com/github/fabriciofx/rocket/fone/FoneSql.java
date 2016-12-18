package com.github.fabriciofx.rocket.fone;

import java.io.IOException;
import java.sql.SQLException;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.util.Config;
import com.github.fabriciofx.rocket.util.ConfigFile;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class FoneSql implements Fone {
	private final Id id;
	private final Database db;
	private final Config queries;
	private final String numero;

	public FoneSql(final Id id, final Database db, final String numero) {
		this(id, db, new ConfigFile("rocket.queries"), numero);
	}
	
	public FoneSql(final Id id, final Database db, final Config queries,
		final String numero) {
		this.id = id;
		this.db = db;
		this.queries = queries;
		this.numero = numero;
	}

	@Override
	public void apaga() throws IOException {
		try {
			new JdbcSession(db.source())
				.sql(queries.read(String.class, "fone.apaga"))
				.set(id)
				.set(numero)
				.update(SingleOutcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
