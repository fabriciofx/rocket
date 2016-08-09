package com.github.fabriciofx.rocket.dominio.pessoa.docs;

import java.io.IOException;
import java.sql.SQLException;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.endereco.SqlEndereco;
import com.github.fabriciofx.rocket.dominio.fone.Fones;
import com.github.fabriciofx.rocket.dominio.fone.SqlFones;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Rg;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Tratamento;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlDocumentos implements Documentos {
	private final transient Database db;
	private final transient Id id;

	public SqlDocumentos(final Database db, final Id id) {
		this.db = db;
		this.id = id;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return fones().print(
			endereco().print(
				tratamento().print(
					sexo().print(
						rg().print(
							cpf().print(media)
						)
					)
				)
			)
		);
	}

	@Override
	public Cpf cpf() throws IOException {
		try {
			return new Cpf(
				new JdbcSession(db.source())
					.sql("SELECT cpf FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}

	@Override
	public Rg rg() throws IOException {
		try {
			return new Rg(
				new JdbcSession(db.source())
					.sql("SELECT rg FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}

	@Override
	public Sexo sexo() throws IOException {
		try {
			return Sexo.valueOf(
				new JdbcSession(db.source())
					.sql("SELECT sexo FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}

	@Override
	public Tratamento tratamento() throws IOException {
		try {
			return Tratamento.valueOf(
				new JdbcSession(db.source())
					.sql("SELECT tratamento FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}

	@Override
	public Endereco endereco() throws IOException {
		return new SqlEndereco(db, id);
	}

	@Override
	public Fones fones() throws IOException {
		return new SqlFones(db, id);
	}
}
