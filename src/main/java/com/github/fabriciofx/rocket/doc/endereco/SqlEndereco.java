package com.github.fabriciofx.rocket.doc.endereco;

import java.io.IOException;
import java.sql.SQLException;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.Identificavel;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlEndereco implements Endereco, Identificavel {
	private final Database db;
	private final Id id;

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
		try {
			final Endereco endereco = new JdbcSession(db.source())
				.sql("SELECT logradouro, numero, complemento, bairro, " +
					"cidade, cep FROM endereco WHERE id = ?")
				.set(id)
				.select(new ListOutcome<Endereco>(new SqlEnderecoMapping()))
				.get(0);
			return endereco.print(media);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}
	
	@Override
	public void atualize(final String logradouro, final String numero,
			final String complemento, final String bairro,
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

	@Override
	public void apague() throws IOException {
	}
}
