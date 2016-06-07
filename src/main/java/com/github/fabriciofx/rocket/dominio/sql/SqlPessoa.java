package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.pessoa.Cpf;
import com.github.fabriciofx.rocket.dominio.pessoa.Pessoa;
import com.github.fabriciofx.rocket.dominio.pessoa.Rg;
import com.github.fabriciofx.rocket.dominio.pessoa.Sexo;
import com.github.fabriciofx.rocket.dominio.pessoa.Tratamento;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.dominio.simples.SimplesEndereco;
import com.github.fabriciofx.rocket.dominio.simples.SimplesPessoa;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.Outcome;

public final class SqlPessoa implements Pessoa, Identificavel<Id> {
	private final transient DataSource ds;
	private final transient Id id;

	public SqlPessoa(final DataSource ds, final Id id) {
		this.ds = ds;
		this.id = id;
	}

	@Override
	public Id id() {
		return id;
	}

	@Override
	public Nome nome() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT nome FROM pessoa WHERE id = ?")
				.set(id.toLong())
				.select(new ListOutcome<Nome>(
						new ListOutcome.Mapping<Nome>() {
							@Override
							public Nome map(final ResultSet rs)
								throws SQLException {
									return new Nome(rs.getString(1));
							}
						}
					)
				).get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void nome(final Nome nome) throws IOException {
		new RestNaoNulo<>().valido(nome);
		try {
			new JdbcSession(ds)
				.sql("UPDATE pessoa SET nome=? WHERE id=?")
				.set(nome.toString())
				.set(id.toLong())
				.update(Outcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Sexo sexo() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT sexo FROM pessoa WHERE id = ?")
				.set(id.toLong())
				.select(new ListOutcome<Sexo>(
						new ListOutcome.Mapping<Sexo>() {
							@Override
							public Sexo map(final ResultSet rs)
								throws SQLException {
									return Sexo.valueOf(rs.getString(1));
							}
						}
					)
				).get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void sexo(final Sexo sexo) throws IOException {
		new RestNaoNulo<>().valido(sexo);
		try {
			new JdbcSession(ds)
				.sql("UPDATE pessoa SET sexo=? WHERE id=?")
				.set(sexo.toString())
				.set(id.toLong())
				.update(Outcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Tratamento tratamento() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT tratamento FROM pessoa WHERE id = ?")
				.set(id.toLong())
				.select(new ListOutcome<Tratamento>(
						new ListOutcome.Mapping<Tratamento>() {
							@Override
							public Tratamento map(final ResultSet rs)
								throws SQLException {
									return Tratamento.valueOf(rs.getString(1));
							}
						}
					)
				).get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void tratamento(final Tratamento tratamento) throws IOException {
		new RestNaoNulo<>().valido(tratamento);		
		try {
			new JdbcSession(ds)
				.sql("UPDATE pessoa SET tratamento=? WHERE id=?")
				.set(tratamento.toString())
				.set(id.toLong())
				.update(Outcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Cpf cpf() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT cpf FROM pessoa WHERE id = ?")
				.set(id.toLong())
				.select(new ListOutcome<Cpf>(
						new ListOutcome.Mapping<Cpf>() {
							@Override
							public Cpf map(final ResultSet rs)
								throws SQLException {
									return new Cpf(rs.getString(1));
							}
						}
					)
				).get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void cpf(final Cpf cpf) throws IOException {
		new RestNaoNulo<>().valido(cpf);		
		try {
			new JdbcSession(ds)
				.sql("UPDATE pessoa SET cpf=? WHERE id=?")
				.set(cpf.toString())
				.set(id.toLong())
				.update(Outcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Rg rg() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT rg FROM pessoa WHERE id = ?")
				.set(id.toLong())
				.select(new ListOutcome<Rg>(
						new ListOutcome.Mapping<Rg>() {
							@Override
							public Rg map(final ResultSet rs)
								throws SQLException {
									return new Rg(rs.getString(1));
							}
						}
					)
				).get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public void rg(Rg rg) throws IOException {
		new RestNaoNulo<>().valido(rg);		
		try {
			new JdbcSession(ds)
				.sql("UPDATE pessoa SET rg=? WHERE id=?")
				.set(rg.toString())
				.set(id.toLong())
				.update(Outcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Endereco endereco() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT logradouro, numero, complemento, bairro, cidade, "
					+ "cep FROM pessoa WHERE id = ?")
				.set(id.toLong())
				.select(new ListOutcome<Endereco>(
						new ListOutcome.Mapping<Endereco>() {
							@Override
							public Endereco map(final ResultSet rs)
								throws SQLException {
									return new SimplesEndereco(
										new Logradouro(rs.getString(1)),
										new Numero(rs.getString(2)),
										new Complemento(rs.getString(3)),
										new Bairro(rs.getString(4)),
										new Cidade(rs.getString(5)),
										new Cep(rs.getString(6))
									);
							}
						}
					)
				).get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public void endereco(final Endereco endereco) throws IOException {
		new RestNaoNulo<>().valido(endereco);		
		try {
			new JdbcSession(ds)
				.sql("UPDATE pessoa SET logradouro=?, numero=?, complemento=?, "
					+ "bairro=?, cidade=?, cep=? WHERE id=?")
				.set(endereco.logradouro().toString())
				.set(endereco.numero().toString())
				.set(endereco.complemento().toString())
				.set(endereco.bairro().toString())
				.set(endereco.cidade().toString())
				.set(endereco.cep().toString())
				.set(id.toLong())
				.update(Outcome.VOID);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Iterable<Fone> fones() throws IOException {
		try {
			final List<Fone> fones = new JdbcSession(ds)
				.sql("SELECT * FROM fone WHERE pessoa = ?")
				.set(id)
				.select(new ListOutcome<Fone>(
					new ListOutcome.Mapping<Fone>() {
						@Override
						public Fone map(final ResultSet rs)
							throws SQLException {
								return new SqlFone(ds, id);
						}
					})
				);
			return fones;
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void fones(final Iterable<Fone> fones) throws IOException {
		new RestNaoVazia<>(
			new RestNaoNulo<>()
		).valido(fones);
		try {
			for (final Fone f : fones) {
				new JdbcSession(ds)
					.sql("UPDATE fone SET numero=?, tipo=?, operadora=? WHERE "
						+ "pessoa=?")
					.set(f.numero())
					.set(f.tipo().toString())
					.set(f.operadora().toString())
					.set(id.toLong())
					.update(Outcome.VOID);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
	
	@Override
	public Media print(Media media) throws IOException {
		try {
			final List<Pessoa> pessoas = new JdbcSession(ds)
				.sql("SELECT * FROM pessoa WHERE id = ?")
				.set(id.toLong())
				.select(new ListOutcome<Pessoa>(
						new ListOutcome.Mapping<Pessoa>() {
							@Override
							public Pessoa map(final ResultSet rs)
								throws SQLException {
								return new SimplesPessoa(
									new Nome(rs.getString(2)),
									Sexo.valueOf(rs.getString(3)),
									Tratamento.valueOf(rs.getString(4)),
									new Cpf(rs.getString(5)),
									new Rg(rs.getString(6)),
									new SimplesEndereco(
										new Logradouro(rs.getString(7)),
										new Numero(rs.getString(8)),
										new Complemento(rs.getString(9)),
										new Bairro(rs.getString(10)),
										new Cidade(rs.getString(11)),
										new Cep(rs.getString(12))
									)
								);
							}
						}
					)
				);
			if (pessoas.size() < 1) {
				throw new IOException(
					String.format("elemento pessoa de id %d não encontrado",
						id.toLong()
					)
				);
			}
			return pessoas.get(0).print(media).with("id", id.toString());
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}
}
