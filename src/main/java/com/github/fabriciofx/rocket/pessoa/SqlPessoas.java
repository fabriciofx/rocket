package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.Transaction;
import com.github.fabriciofx.rocket.doc.fone.Fones;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.NumId;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoas implements Pessoas {
	private final Database db;

	public SqlPessoas(final Database db) {
		this.db = db;
	}
	
	@Override
	public Pessoa pessoa(final Map<String, String> documentos,
		final Fones fones) throws IOException {
		return new Transaction(db).call(
			new Callable<Pessoa>() {
				@Override
				public Pessoa call() throws Exception {
					final Id id = new NumId(
						new JdbcSession(db.source())
						.sql("INSERT INTO pessoa (nome, cpf, rg, sexo, tratamento, " +
							"logradouro, numero, complemento, bairro, cidade, cep) " +
							"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
						.set(documentos.get("nome"))
						.set(documentos.get("cpf"))
						.set(documentos.get("rg"))
						.set(documentos.get("sexo"))				
						.set(documentos.get("tratamento"))
						.set(documentos.get("logradouro"))
						.set(documentos.get("numero"))
						.set(documentos.get("complemento"))
						.set(documentos.get("bairro"))
						.set(documentos.get("cidade"))
						.set(documentos.get("cep"))
						.insert(SingleOutcome.LAST_INSERT_ID)
					);
					return new SqlPessoa(db, id);
				}				
			}
		);
	}

	@Override
	public Pessoa pessoa(final Id id) throws IOException {
		return new SqlPessoa(db, id);
	}

	@Override
	public List<Pessoa> todas() throws IOException {
		try {
			return new JdbcSession(db.source())
				.sql("SELECT id FROM pessoa")
				.select(new ListOutcome<Pessoa>(new SqlPessoaMapping()));
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}
	
	private final class SqlPessoaMapping implements ListOutcome.Mapping<Pessoa> {
		@Override
		public Pessoa map(final ResultSet rs) throws SQLException {
			return new SqlPessoa(db, new NumId(rs.getInt(1)));
		}		
	}
}
