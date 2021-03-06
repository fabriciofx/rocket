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
import com.github.fabriciofx.rocket.media.XmlMedia;
import com.github.fabriciofx.rocket.sql.TableMappingSql;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class PessoaJuridicaSql implements Pessoa {
	private final Id id;
	private final Database db;
	private final Media media;
	private final Config queries;

	public PessoaJuridicaSql(final Id id, final Database db) {
		this(id, db, new XmlMedia("pessoa-juridica"));
	}

	public PessoaJuridicaSql(final Id id, final Database db,
		final Media media) {
		this(id, db, media, new ConfigFile("rocket.queries"));
	}
	
	public PessoaJuridicaSql(final Id id, final Database db, final Media media,
		final Config queries) {
		this.id = id;
		this.db = db;
		this.media = media;
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
				.sql(queries.value("pessoa_juridica.documentos"))
				.set(documentos.get("cnpj"))
				.set(documentos.get("inscricao_estadual"))
				.set(documentos.get("inscricao_municipal"))
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
	public Media about() throws IOException {
		try {
			final Map<String, String> documentos = new JdbcSession(db.source())
				.sql(queries.value("pessoa_juridica.about"))
				.set(id)
				.select(
					new ListOutcome<Map<String, String>>(
						new TableMappingSql()
					)
				)
				.get(0);
			return new PessoaSql(id, db, media).about()
				.with("cnpj", documentos.get("cnpj"))
				.with("inscricao_estadual", documentos.get("inscricao_estadual"))
				.with("inscricao_municipal", documentos.get("inscricao_municipal"));
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
