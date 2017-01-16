package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.fone.Fones;
import com.github.fabriciofx.rocket.fone.FonesSql;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.util.Config;
import com.github.fabriciofx.rocket.util.ConfigFile;
import com.github.fabriciofx.rocket.util.TableMappingSql;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class PessoaFisicaSql implements Pessoa {
	private final Id id;
	private final Database db;
	private final Config queries;
	
	public PessoaFisicaSql(final Id id, final Database db) {
		this(id, db, new ConfigFile("rocket.queries"));
	}
	
	public PessoaFisicaSql(final Id id, final Database db,
		final Config queries) {
		this.id = id;
		this.db = db;
		this.queries = queries;
	}

	@Override
	public Id id() {
		return id;
	}	

	@Override
	public void renomeia(final String nome) throws IOException {
		new PessoaSql(id, db).renomeia(nome);
	}

	@Override
	public void documentos(final Map<String, String> documentos)
		throws IOException {
		try {
			new JdbcSession(db.source())
				.sql(queries.value(String.class, "pessoa.fisica.documentos"))
				.set(documentos.get("tratamento"))
				.set(documentos.get("cpf"))
				.set(documentos.get("rg"))
				.set(documentos.get("sexo"))
				.set(id)
				.update(SingleOutcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Fones fones() throws IOException {
		return new FonesSql(id, db);
	}
	
	@Override
	public Media about(final Media media) throws IOException {
		try {
			final Map<String, String> documentos = new JdbcSession(db.source())
				.sql(queries.value(String.class, "pessoa.fisica.about"))
				.set(id)
				.select(
					new ListOutcome<Map<String, String>>(
						new TableMappingSql()
					)
				)
				.get(0);
			return new PessoaSql(id, db).about(media)
				.with("tratamento", documentos.get("tratamento"))
				.with("cpf", documentos.get("cpf"))
				.with("rg", documentos.get("rg"))
				.with("sexo", documentos.get("sexo"));
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
