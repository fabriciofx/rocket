package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.pessoa.Cpf;
import com.github.fabriciofx.rocket.dominio.pessoa.Pessoa;
import com.github.fabriciofx.rocket.dominio.pessoa.Pessoas;
import com.github.fabriciofx.rocket.dominio.pessoa.Rg;
import com.github.fabriciofx.rocket.dominio.pessoa.Sexo;
import com.github.fabriciofx.rocket.dominio.pessoa.Tratamento;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.NumId;
import com.github.fabriciofx.rocket.infra.repositorio.Transacao;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.Outcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoas implements Pessoas {
	private final DataSource ds;

	public SqlPessoas(final DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void pessoa(final Nome nome, final Sexo sexo,
			final Tratamento tratamento, final Cpf cpf, final Rg rg,
			final Endereco endereco, final Fone... fones) throws IOException {
		final Transacao transacao = new Transacao(ds);
		final JdbcSession session = transacao.session();
		transacao.execute(
			new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					final Id id = insert(session, nome, sexo, tratamento, cpf,
						rg, endereco);
					insert(session, id, fones);
					return null;
				}
			}
		);		
	}
	
	private void insert(final JdbcSession session, final Id id,
		final Fone... fones) throws SQLException {
		for (final Fone f : fones) {
			session.sql("INSERT INTO fone (pessoa, numero, tipo, operadora) "
				+ "VALUES (?, ?, ?, ?)")
			.set(id.toLong())
			.set(f.numero())
			.set(f.tipo())
			.set(f.operadora())
			.insert(Outcome.VOID);
		}
	}
	
	private Id insert(final JdbcSession session, final Nome nome,
			final Sexo sexo, final Tratamento tratamento, final Cpf cpf,
			final Rg rg, final Endereco endereco) throws SQLException {
		return new NumId(session
			.sql("INSERT INTO pessoa (nome, sexo, tratamento, cpf, rg, "
				+ "logradouro, numero, complemento, bairro, cidade, cep) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ? ,? ,?, ?, ?)")
			.set(nome.toString())
			.set(sexo.toString())
			.set(tratamento.toString())
			.set(cpf.toString())
			.set(rg.toString())
			.set(endereco.logradouro().toString())
			.set(endereco.numero().toString())
			.set(endereco.complemento().toString())
			.set(endereco.bairro().toString())
			.set(endereco.cidade().toString())
			.set(endereco.cep().toString())
			.insert(new SingleOutcome<Long>(Long.class, true))
		);
	}

	@Override
	public Pessoa pessoa(final Id id) throws IOException {
		try {
			final List<Pessoa> pessoas = new JdbcSession(ds)
			.sql("SELECT * FROM pessoa WHERE id = ?")
			.set(id.toLong())
			.select(new ListOutcome<Pessoa>(
					new ListOutcome.Mapping<Pessoa>() {
						@Override
						public Pessoa map(final ResultSet rs)
							throws SQLException {
							return new SqlPessoa(ds, new NumId(rs.getLong(1)));
						}
					}
				)
			);
			if (pessoas.isEmpty()) {
				throw new IllegalArgumentException(
					String.format("pessoa com id \"%d\" não encontrado",
						id.toLong()
					)
				);
			}
			return pessoas.get(0);			
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
		
	@Override
	public Iterable<Pessoa> todos() throws IOException {
		try {
			return new JdbcSession(ds)
			.sql("SELECT * FROM pessoa")
			.select(new ListOutcome<Pessoa>(
					new ListOutcome.Mapping<Pessoa>() {
						@Override
						public Pessoa map(final ResultSet rs)
							throws SQLException {
							return new SqlPessoa(ds, new NumId(rs.getLong(1)));
						}
					}
				)
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
