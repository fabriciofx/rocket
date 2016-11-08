package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.Transaction;
import com.github.fabriciofx.rocket.doc.Cpf;
import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.doc.Rg;
import com.github.fabriciofx.rocket.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.doc.fone.Fones;
import com.github.fabriciofx.rocket.doc.fone.SqlFones;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.NumId;
import com.github.fabriciofx.rocket.id.UuidId;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlPessoas implements Pessoas {
	private final Database db;

	public SqlPessoas(final Database db) {
		this.db = db;
	}
	
	@Override
	public Pessoa pessoa(final Nome nome, final Cpf cpf, final Rg rg,
		final Endereco endereco, final Fones fones) throws IOException {
		return new Transaction(db).call(
			new Callable<Pessoa>() {
				@Override
				public Pessoa call() throws Exception {
					final Id id = new SqlPessoa(
						db, new UuidId()
					).adicione(nome, cpf, rg, endereco);
					new SqlFones(db, id).adicione();
					return new SqlPessoa(db, id);
				}				
			}
		);
	}

	@Override
	public SqlPessoa pessoa(final Id id) throws IOException {
		return new SqlPessoa(db, id);
	}

	@Override
	public List<Pessoa> todas() throws IOException {
		try {
			return new JdbcSession(db.source())
				.sql("SELECT id FROM pessoa")
				.select(new ListOutcome<Pessoa>(new PessoaMapping()));
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}
	
	private final class PessoaMapping implements ListOutcome.Mapping<Pessoa> {
		@Override
		public SqlPessoa map(final ResultSet rs) throws SQLException {
			return new SqlPessoa(db, new NumId(rs.getLong(1)));
		}		
	}
}
