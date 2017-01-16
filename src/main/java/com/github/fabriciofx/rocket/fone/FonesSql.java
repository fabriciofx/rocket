package com.github.fabriciofx.rocket.fone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.util.Config;
import com.github.fabriciofx.rocket.util.ConfigFile;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public class FonesSql implements Fones {
	private final Id id;
	private final Database db;
	private final Config queries;
	
	public FonesSql(final Id id, final Database db) {
		this(id, db, new ConfigFile("rocket.queries"));
	}
	
	public FonesSql(final Id id, final Database db, final Config queries) {
		this.id = id;
		this.db = db;
		this.queries = queries;
	}

	@Override
	public Id id() {
		return id;
	}

	@Override
	public Fone fone(final String numero, final String tipo,
		final String operadora) throws IOException {
		try {
			new JdbcSession(db.source())
				.sql(queries.value(String.class, "fones.fone"))
				.set(id)
				.set(numero)
				.set(tipo)
				.set(operadora)
				.insert(SingleOutcome.VOID);
			return new FoneSql(id, db, numero);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public List<Fone> todos() throws IOException {
		return Collections.emptyList();
	}
}
