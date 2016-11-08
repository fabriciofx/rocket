package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.doc.Cpf;
import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.doc.Rg;
import com.github.fabriciofx.rocket.doc.endereco.Cep;
import com.github.fabriciofx.rocket.doc.endereco.Cidade;
import com.github.fabriciofx.rocket.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.doc.endereco.MemEndereco;
import com.github.fabriciofx.rocket.doc.fone.Fones;
import com.github.fabriciofx.rocket.doc.fone.SqlFones;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.Identificavel;
import com.github.fabriciofx.rocket.id.NumId;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoa implements Pessoa, Identificavel {
	private final Database db;
	private final Id id;

	public SqlPessoa(final Database db, final Id id) {
		this.db = db;
		this.id = id;
	}
		
	@Override
	public Id id() {
		return id;
	}
		
	@Override
	public Media print(final Media media) throws IOException {
		try {
			final Pessoa pessoa = new JdbcSession(db.source())
				.sql("SELECT nome, cpf, rg, logradouro, numero, complemento, " +
					 "bairro, cidade, cep FROM pessoa WHERE id = ?")
				.set(id)
				.select(new ListOutcome<Pessoa>(new PessoaMapping()))
				.get(0);
			return pessoa.print(media);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public Nome nome() throws IOException {
		try {
			return new Nome(
				new JdbcSession(db.source())
					.sql("SELECT nome FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Fones fones() throws IOException {
		return new SqlFones(db, id);
	}

	@Override
	public void atualize(final Nome nome, final Cpf cpf, final Rg rg,
			final Endereco endereco)
			throws IOException {
		try {
			new JdbcSession(db.source())
				.sql("UPDATE pessoa SET nome = ?, cpf = ?, rg = ?, " +
					"logradouro = ?, numero = ?, complemento = ?, " +
					"bairro = ?, cidade = ?, cep = ? WHERE id = ?")
				.set(nome)
				.set(cpf)
				.set(rg)
				.set(id)
				.execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	public Id adicione(final Nome nome, final Cpf cpf, final Rg rg,
		final Endereco endereco) throws IOException {
		try {
			return new NumId(
				new JdbcSession(db.source())
				.sql("INSERT INTO pessoa (nome, cpf, rg, endereco) "
					+ "VALUES (?, ?, ?, ?)")
				.set(nome)
				.set(cpf.toString())
				.set(rg.toString())
				.set(endereco.toString())
				.insert(SingleOutcome.LAST_INSERT_ID)
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}	

	private class PessoaMapping implements ListOutcome.Mapping<Pessoa> {
		@Override
		public Pessoa map(final ResultSet rs) throws SQLException {
			return new MemPessoa(
				new Nome(rs.getString(1)),
				new Cpf(rs.getString(2)),
				new Rg(rs.getString(3)),
				new MemEndereco(
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					new Cidade(rs.getString(8)),
					new Cep(rs.getString(9))
				),
				new SqlFones(db, id)
			);
		}		
	}
}
