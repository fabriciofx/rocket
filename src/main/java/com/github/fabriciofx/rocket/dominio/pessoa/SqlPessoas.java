package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.UuidId;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlPessoas implements Pessoas {
	private final transient DataSource ds;

	public SqlPessoas(final DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Pessoa pessoa(final Id id) throws IOException {
		return new SqlPessoa(ds, id);
	}

	@Override
	public List<Pessoa> todas() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT id FROM pessoa")
				.select(new ListOutcome<Pessoa>(new PessoaMapping()));
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}
	
	private class PessoaMapping implements ListOutcome.Mapping<Pessoa> {
		@Override
		public Pessoa map(final ResultSet rs) throws SQLException {
			return new SqlPessoa(ds, new UuidId(rs.getString(1)));
		}		
	}
}
