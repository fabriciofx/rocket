package com.github.fabriciofx.rocket.pessoas;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.Callable;

import com.github.fabriciofx.rocket.config.Config;
import com.github.fabriciofx.rocket.config.ConfigFile;
import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.Transaction;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.IdNum;
import com.github.fabriciofx.rocket.pessoa.Pessoa;
import com.github.fabriciofx.rocket.pessoa.PessoaFisicaSql;
import com.github.fabriciofx.rocket.pessoa.PessoaJuridicaSql;
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
	public Pessoa fisica(final String nome,
		final Map<String, String> documentos) throws IOException {
		return new Transaction(db).call(
			new Callable<Pessoa>() {
				@Override
				public Pessoa call() throws Exception {
					try {
						final JdbcSession session = new JdbcSession(db.source());
						final Id id = new IdNum(
							session
							.sql(queries.value("pessoas.pessoa"))
							.set(nome)
							.set(documentos.get("email"))
							.set(documentos.get("logradouro"))
							.set(documentos.get("numero"))
							.set(documentos.get("complemento"))
							.set(documentos.get("bairro"))
							.set(documentos.get("cidade"))
							.set(documentos.get("cep"))
							.insert(SingleOutcome.LAST_INSERT_ID)
						);
						session
							.sql(queries.value("pessoas.pessoa.fisica"))
							.set(id)
							.set(documentos.get("tratamento"))
							.set(documentos.get("cpf"))
							.set(documentos.get("rg"))
							.set(documentos.get("sexo"))
							.insert(SingleOutcome.VOID);
						return new PessoaFisicaSql(id, db);
					} catch (final SQLException e) {
						throw new IOException(e);
					}
				}
			}
		);
	}

	@Override
	public Pessoa juridica(final String nome,
		final Map<String, String> documentos) throws IOException {
		return new Transaction(db).call(
			new Callable<Pessoa>() {
				@Override
				public Pessoa call() throws Exception {
					try {
						final Id id = new IdNum(
							new JdbcSession(db.source())
							.sql(queries.value("pessoas.pessoa"))
							.set(nome)
							.set(documentos.get("email"))
							.set(documentos.get("logradouro"))
							.set(documentos.get("numero"))
							.set(documentos.get("complemento"))
							.set(documentos.get("bairro"))
							.set(documentos.get("cidade"))
							.set(documentos.get("cep"))
							.insert(SingleOutcome.LAST_INSERT_ID)
						);
						new JdbcSession(db.source())
							.sql(queries.value("pessoas.pessoa.juridica"))
							.set(id)
							.set(documentos.get("cnpj"))
							.set(documentos.get("inscricao_estadual"))
							.set(documentos.get("inscricao_municipal"))
							.insert(SingleOutcome.VOID);
						return new PessoaJuridicaSql(id, db);
					} catch (final SQLException e) {
						throw new IOException(e);
					}
				}
			}
		);
	}
	
	@Override
	public Pessoa pessoa(final Id id) {
		return new PessoaFisicaSql(id, db);
	}
}
