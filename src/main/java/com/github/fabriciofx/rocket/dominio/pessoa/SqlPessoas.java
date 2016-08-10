package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.Transaction;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.fone.Fones;
import com.github.fabriciofx.rocket.dominio.fone.SqlFones;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.Documentos;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.NumId;
import com.github.fabriciofx.rocket.id.UuidId;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlPessoas implements Pessoas<SqlPessoa> {
	private final transient Database db;

	public SqlPessoas(final Database db) {
		this.db = db;
	}
	
	@Override
	public SqlPessoa pessoa(final Nome nome, final Documentos documentos,
		final Fones fones) throws IOException {
		return new Transaction(db).call(
			new Callable<SqlPessoa>() {
				@Override
				public SqlPessoa call() throws Exception {
					final Id id = new SqlPessoa(
						db, new UuidId()
					).adiciona(nome, documentos);
					new SqlFones(db, id).adiciona(fones);
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
	public List<SqlPessoa> todas() throws IOException {
		try {
			return new JdbcSession(db.source())
				.sql("SELECT id FROM pessoa")
				.select(new ListOutcome<SqlPessoa>(new PessoaMapping()));
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}
	
	private class PessoaMapping implements ListOutcome.Mapping<SqlPessoa> {
		@Override
		public SqlPessoa map(final ResultSet rs) throws SQLException {
			return new SqlPessoa(db, new NumId(rs.getLong(1)));
		}		
	}
}
