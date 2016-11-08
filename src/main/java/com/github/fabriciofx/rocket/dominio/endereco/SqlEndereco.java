package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;
import java.sql.SQLException;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.doc.endereco.Bairro;
import com.github.fabriciofx.rocket.doc.endereco.Cep;
import com.github.fabriciofx.rocket.doc.endereco.Cidade;
import com.github.fabriciofx.rocket.doc.endereco.Complemento;
import com.github.fabriciofx.rocket.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.doc.endereco.Logradouro;
import com.github.fabriciofx.rocket.doc.endereco.Numero;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.Identificavel;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlEndereco implements Endereco, Identificavel {
	private final transient Database db;
	private final transient Id id;

	public SqlEndereco(final Database db, final Id id) {
		this.db = db;
		this.id = id;
	}

	@Override
	public Id id() {
		return id;
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
				new JdbcSession(db.source())
					.sql("SELECT logradouro FROM endereco WHERE id = ?")
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
				new JdbcSession(db.source())
					.sql("SELECT numero FROM endereco WHERE id = ?")
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
				new JdbcSession(db.source())
					.sql("SELECT complemento FROM endereco WHERE id = ?")
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
				new JdbcSession(db.source())
					.sql("SELECT bairro FROM endereco WHERE id = ?")
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
				new JdbcSession(db.source())
					.sql("SELECT cidade FROM endereco WHERE id = ?")
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
				new JdbcSession(db.source())
					.sql("SELECT cep FROM endereco WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}
	
	public void atualiza(final Logradouro logradouro, final Numero numero,
			final Complemento complemento, final Bairro bairro,
			final Cidade cidade, final Cep cep) throws IOException {
		try {
			new JdbcSession(db.source())
				.sql("UPDATE endereco SET logradouro = ?, numero = ?, "
					+ "complemento = ?, bairro = ?, cidade = ?, cep = ? "
					+ "WHERE id = ?")
				.set(logradouro)
				.set(numero)
				.set(complemento)
				.set(bairro)
				.set(cidade)
				.set(cep)
				.set(id)
				.execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
