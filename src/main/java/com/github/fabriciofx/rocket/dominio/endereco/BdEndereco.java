package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.endereco.doc.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Numero;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class BdEndereco implements Endereco {
	private final transient DataSource ds;
	private final transient Id id;

	public BdEndereco(final DataSource ds, final Id id) {
		this.ds = ds;
		this.id = id;
	}
	
	@Override
	public Media print(final Media media) throws IOException {
		return cep().print(
			cidade().print(
				bairro().print(
					complemento().print(
						numero().print(
							logradouro().print(media)
						)
					)
				)
			)
		);
	}

	@Override
	public Logradouro logradouro() throws IOException {
		try {
			return new Logradouro(
				new JdbcSession(ds)
					.sql("SELECT logradouro FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public Numero numero() throws IOException {
		try {
			return new Numero(
				new JdbcSession(ds)
					.sql("SELECT numero FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public Complemento complemento() throws IOException {
		try {
			return new Complemento(
				new JdbcSession(ds)
					.sql("SELECT complemento FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Bairro bairro() throws IOException {
		try {
			return new Bairro(
				new JdbcSession(ds)
					.sql("SELECT bairro FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public Cidade cidade() throws IOException {
		try {
			return new Cidade(
				new JdbcSession(ds)
					.sql("SELECT cidade FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public Cep cep() throws IOException {
		try {
			return new Cep(
				new JdbcSession(ds)
					.sql("SELECT cep FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}
}
