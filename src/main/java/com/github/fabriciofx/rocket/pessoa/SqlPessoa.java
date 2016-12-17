package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlPessoa implements Pessoa {
	private final Id id;
	private final Database db;
	
	public SqlPessoa(final Id id, final Database db) {
		this.id = id;
		this.db = db;
	}

	@Override
	public Id id() {
		return id;
	}	
	
	@Override
	public Media about(final Media media) throws IOException {
		try {
			final Map<String, String> documentos = new JdbcSession(db.source())
				.sql("SELECT * FROM pessoa WHERE id = ?")
				.set(id)
				.select(
					new ListOutcome<Map<String, String>>(
						new SqlTableMapping()
					)
				)
				.get(0);
			return media
				.with("id", id)
				.with("nome", documentos.get("nome"))
				.with("endereco", documentos.get("endereco"))
				.with("fone", documentos.get("fone"));
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
