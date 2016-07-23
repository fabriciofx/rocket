package com.github.fabriciofx.rocket.dominio.bd;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Documentos;
import com.github.fabriciofx.rocket.dominio.Pessoa;
import com.github.fabriciofx.rocket.dominio.doc.Nome;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class BdPessoa implements Pessoa {
	private final transient DataSource ds;
	private final transient Id id;

	public BdPessoa(final DataSource ds, final Id id) {
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
			return new Nome(
				new JdbcSession(ds)
					.sql("SELECT nome FROM pessoa WHERE id = ?")
					.set(id)
					.select(new SingleOutcome<String>(String.class))
			);
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	@Override
	public Documentos documentos() throws IOException {
		return new BdDocumentos(ds, id);
	}

	@Override
	public Media print(final Media media) throws IOException {
		return documentos().print(
				nome().print(
					media.with("id", id)
			)
		);
	}	

	@Override
	public Pessoa salva(final Pessoa origem) throws IOException {
		final JdbcSession session = new JdbcSession(ds);
		try {
			session.sql("INSERT INTO pessoa (id, nome, cpf, rg, sexo,"
					+ "tratamento, logradouro, numero, complemento, bairro, "
					+ "cidade, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
				.set(id)
				.set(origem.nome())
				.set(origem.documentos().cpf())
				.set(origem.documentos().rg())
				.set(origem.documentos().sexo())
				.set(origem.documentos().tratamento())
				.set(origem.documentos().endereco().logradouro())
				.set(origem.documentos().endereco().numero())
				.set(origem.documentos().endereco().complemento())
				.set(origem.documentos().endereco().bairro())
				.set(origem.documentos().endereco().cidade())
				.set(origem.documentos().endereco().cep())
				.insert(SingleOutcome.VOID);
			documentos().fones().salva(origem.documentos().fones());
			return new BdPessoa(ds, id);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}	
}
