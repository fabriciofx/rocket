package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.documento.Cpf;
import com.github.fabriciofx.rocket.dominio.documento.Nome;
import com.github.fabriciofx.rocket.dominio.documento.Rg;
import com.github.fabriciofx.rocket.dominio.documento.Sexo;
import com.github.fabriciofx.rocket.dominio.documento.Tratamento;
import com.github.fabriciofx.rocket.dominio.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.endereco.Estado;
import com.github.fabriciofx.rocket.dominio.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.pessoa.Pessoa;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.dominio.repositorio.NumId;
import com.github.fabriciofx.rocket.media.InsertSqlMedia;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;
import com.github.fabriciofx.rocket.repository.Repository;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoa
		implements Identificavel<Id>, Repository<SqlPessoa>, Printer {
	private final transient Pessoa origem;
	private final transient Id id;

	public SqlPessoa(final Pessoa origem, final Id id) {
		this.origem = origem;
		this.id = id;
	}

	@Override
	public Id id() {
		return id;
	}

	@Override
	public Media print(final Media media) throws IOException { 
		return origem.print(media.with("id", id.toString()));
	}

	@Override
	public void save(final DataSource ds) throws IOException {
		final String sql = print(new InsertSqlMedia("pessoa")).toString();
		try {
			new JdbcSession(ds)
				.sql(sql)
				.insert(new SingleOutcome<Long>(Long.class, true));
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public SqlPessoa find(final DataSource ds, final Id id) throws IOException {
		try {
			final List<SqlPessoa> pessoas = new JdbcSession(ds)
				.sql("SELECT * FROM pessoa WHERE id = ?")
				.set(id.toLong())
				.select(new ListOutcome<SqlPessoa>(
						new ListOutcome.Mapping<SqlPessoa>() {
							@Override
							public SqlPessoa map(final ResultSet rs)
								throws SQLException {
								return new SqlPessoa(
									new Pessoa(
										new Nome(rs.getString(2)),
										Sexo.valueOf(rs.getString(3)),
										Tratamento.valueOf(rs.getString(4)),
										new Cpf(rs.getString(5)),
										new Rg(rs.getString(6)),
										new Endereco(
											new Logradouro(rs.getString(7)),
											new Numero(rs.getString(8)),
											new Complemento(rs.getString(9)),
											new Bairro(rs.getString(10)),
											new Cidade(
												rs.getString(11),
												Estado.valueOf(rs.getString(12))
											),
											new Cep(rs.getString(13))
										)
									), new NumId(rs.getLong(1))
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
			return pessoas.get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
