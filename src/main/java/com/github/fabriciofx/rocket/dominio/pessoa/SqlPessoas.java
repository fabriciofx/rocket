package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.Documentos;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.NumId;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoas implements Pessoas {
	private final transient DataSource ds;

	public SqlPessoas(final DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Pessoa pessoa(final Nome nome, final Documentos documentos)
			throws IOException {
		try {
			final long id = new JdbcSession(ds)
				.sql("INSERT INTO pessoa (nome, cpf, rg, sexo, tratamento, "
					+ "logradouro, numero, complemento, bairro, cidade, cep) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
				.set(nome)
				.set(documentos.cpf())
				.set(documentos.rg())
				.set(documentos.sexo())
				.set(documentos.tratamento())
				.set(documentos.endereco().logradouro())
				.set(documentos.endereco().numero())
				.set(documentos.endereco().complemento())
				.set(documentos.endereco().bairro())
				.set(documentos.endereco().cidade())
				.set(documentos.endereco().cep())
				.insert(SingleOutcome.LAST_INSERT_ID);
			return new SqlPessoa(ds, new NumId(id));
		} catch (final SQLException e) {
			throw new IOException(e);
		}
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
			return new SqlPessoa(ds, new NumId(rs.getLong(1)));
		}		
	}
}
