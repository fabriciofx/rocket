package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.repositorio.NumId;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlEnderecos implements Enderecos {
	private final DataSource ds;
	
	public SqlEnderecos(final DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Endereco endereco(final Logradouro logradouro,final Numero numero,
			final Complemento complemento, final Bairro bairro,
			final Cidade cidade, final Cep cep) throws IOException {
		try {
			final long id = new JdbcSession(ds)
				.sql("INSERT INTO endereco (logradouro, numero, complemento, " +
					 "bairro, cidade, cep) VALUES (?, ?, ?, ?, ?, ?)")
				.set(logradouro.toString())
				.set(numero.toString())
				.set(complemento.toString())
				.set(bairro.toString())
				.set(cidade.toString())
				.set(cep.toString())
				.insert(new SingleOutcome<Long>(Long.class, true));
			return new SqlEndereco(ds, new NumId(id));
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Iterable<Endereco> iterate() throws IOException {
		try {
			final List<Endereco> enderecos = new JdbcSession(ds)
				.sql("SELECT * FROM endereco")
				.select(new ListOutcome<Endereco>(
					new ListOutcome.Mapping<Endereco>() {
						@Override
						public Endereco map(final ResultSet rs)
							throws SQLException {
							return new SimplesEndereco(
								new Logradouro(rs.getString(2)),
								new Numero(rs.getString(3)),
								new Complemento(rs.getString(4)),
								new Bairro(rs.getString(5)),
								new Cidade(rs.getString(6)),
								new Cep(rs.getString(7))
							);
						}
					})
				);
			return enderecos;
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
