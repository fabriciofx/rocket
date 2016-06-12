package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.dominio.simples.SimplesEndereco;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlEndereco implements Endereco, Identificavel<Id> {
	private final DataSource ds;
	private final Id id;

	public SqlEndereco(final DataSource ds, final Id id) {
		this.ds = new NotNull<DataSource>().valid(ds);
		this.id = new NotNull<Id>().valid(id);
	}

	@Override
	public Id id() {
		return id;
	}

	@Override
	public Media print(Media media) throws IOException {
		return endereco(id.toLong()).print(media).with("id", id.toString());
	}

	private Endereco endereco(final long id) throws IOException {
		try {
			final List<Endereco> enderecos = new JdbcSession(ds)
				.sql("SELECT * FROM endereco WHERE id = ?")
				.set(id)
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
			if (enderecos.size() < 1) {
				throw new IOException("objeto endereço não encontrado");
			}
			return enderecos.get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Logradouro logradouro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logradouro(Logradouro logradouro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Numero numero() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void numero(Numero numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Complemento complemento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void complemento(Complemento complemento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bairro bairro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bairro(Bairro bairro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cidade cidade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cidade(Cidade cidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cep cep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cep(Cep cep) {
		// TODO Auto-generated method stub
		
	}
}
