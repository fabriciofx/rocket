package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlPessoaFisica implements PessoaFisica {
	private final Pessoa origem;
	private final Database db;
	
	public SqlPessoaFisica(final Pessoa origem, final Database db) {
		this.origem = origem;
		this.db = db;
	}

	@Override
	public Id id() {
		return origem.id();
	}

	@Override
	public Media about(final Media media) throws IOException {
		try {
			final Map<String, String> documentos = new JdbcSession(db.source())
				.sql("SELECT cpf, rg FROM pessoa_fisica WHERE id = ?")
				.set(origem.id())
				.select(
					new ListOutcome<Map<String, String>>(
						new SqlTableMapping()
					)
				)
				.get(0);
			return origem.about(media)
				.with("cpf", documentos.get("cpf"))
				.with("rg", documentos.get("rg"));
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}
}
