package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.github.fabriciofx.rocket.config.Config;
import com.github.fabriciofx.rocket.config.ConfigFile;
import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.fone.Fones;
import com.github.fabriciofx.rocket.fone.FonesSql;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.sql.TableMappingSql;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class PessoaSql implements Pessoa {
	private final Id id;
	private final Database db;
	private final Config queries;
	
	public PessoaSql(final Id id, final Database db) {
		this(id, db, new ConfigFile("rocket.queries"));
	}
	
	public PessoaSql(final Id id, final Database db, final Config queries) {
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
		try {
			new JdbcSession(db.source())
				.sql(queries.value(String.class, "pessoa.renomeia"))
				.set(nome)
				.set(id)
				.update(SingleOutcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void documentos(final Map<String, String> documentos)
		throws IOException {
		try {
			new JdbcSession(db.source())
				.sql(queries.value(String.class, "pessoa.documentos"))
				.set(documentos.get("email"))
				.set(documentos.get("logradouro"))
				.set(documentos.get("numero"))
				.set(documentos.get("complemento"))
				.set(documentos.get("bairro"))
				.set(documentos.get("cidade"))
				.set(documentos.get("cep"))
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
				.sql(queries.value(String.class, "pessoa.about"))
				.set(id)
				.select(
					new ListOutcome<Map<String, String>>(
						new TableMappingSql()
					)
				)
				.get(0);
			return media
				.with("id", id)
				.with("nome", documentos.get("nome"))
				.with("email", documentos.get("email"))
				.with("logradouro", documentos.get("logradouro"))
				.with("numero", documentos.get("numero"))
				.with("complemento", documentos.get("complemento"))
				.with("bairro", documentos.get("bairro"))
				.with("cidade", documentos.get("cidade"))
				.with("cep", documentos.get("cep"));
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
