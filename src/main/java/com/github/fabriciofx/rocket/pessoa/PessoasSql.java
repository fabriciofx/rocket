package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.NumId;
import com.github.fabriciofx.rocket.util.Config;
import com.github.fabriciofx.rocket.util.ConfigFile;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class PessoasSql implements Pessoas {
	private final Database db;
	private final Config queries;

	public PessoasSql(final Database db) {
		this(db, new ConfigFile("rocket.queries"));
	}
	
	public PessoasSql(final Database db, final Config queries) {
		this.db = db;
		this.queries = queries;
	}
	
	@Override
	public Pessoa pessoa(final String nome,
		final Map<String, String> documentos) throws IOException {
		try {
			final Id id = new NumId(
				new JdbcSession(db.source())
					.sql(queries.read(String.class, "pessoas.pessoa"))
					.set(nome)
					.set(documentos.get("tratamento"))
					.set(documentos.get("cpf"))
					.set(documentos.get("rg"))
					.set(documentos.get("sexo"))
					.set(documentos.get("logradouro"))
					.set(documentos.get("numero"))
					.set(documentos.get("complemento"))
					.set(documentos.get("bairro"))
					.set(documentos.get("cidade"))
					.set(documentos.get("cep"))
					.set(documentos.get("email"))
					.insert(SingleOutcome.LAST_INSERT_ID)
				);
			return new PessoaSql(id, db);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public Pessoa pessoa(final Id id) {
		return new PessoaSql(id, db);
	}
}
