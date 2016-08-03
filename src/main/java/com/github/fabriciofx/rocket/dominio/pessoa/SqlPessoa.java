package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.endereco.SimplesEndereco;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Numero;
import com.github.fabriciofx.rocket.dominio.fone.SqlFones;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.Documentos;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.SimplesDocumentos;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Rg;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Tratamento;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.Identificavel;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoa implements Pessoa, Identificavel {
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
	public Media print(final Media media) throws IOException {
		return documentos().print(
				nome().print(
					media.with("id", id)
			)
		);
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
		try {
			return new JdbcSession(ds)
				.sql("SELECT cpf, rg, sexo, tratamento, logradouro, numero, "
					+ "complemento, bairro, cidade, cep FROM pessoa "
					+ "WHERE id = ?")
				.set(id)
				.select(new ListOutcome<Documentos>(new DocumentosMapping()))
				.get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}
	
	private class DocumentosMapping implements ListOutcome.Mapping<Documentos> {
		@Override
		public Documentos map(final ResultSet rs) throws SQLException {
			return new SimplesDocumentos(
				new Cpf(rs.getString(1)),
				new Rg(rs.getString(2)),
				Sexo.valueOf(rs.getString(3)),
				Tratamento.valueOf(rs.getString(4)),
				new SimplesEndereco(
					new Logradouro(rs.getString(5)),
					new Numero(rs.getString(6)),
					new Complemento(rs.getString(7)),
					new Bairro(rs.getString(8)),
					new Cidade(rs.getString(9)),
					new Cep(rs.getString(7))
				),
				new SqlFones(ds, id)
			);
		}		
	}

	public void atualiza(final Nome nome, final Documentos documentos)
			throws IOException {
		try {
			new JdbcSession(ds)
				.sql("UPDATE pessoa SET nome = ?, cpf = ?, rg = ?, sexo = ?, "
						+ "tratamento = ?, logradouro = ?, numero = ?, "
						+ "complemento = ?, bairro = ?, cidade = ?, cep = ? "
						+ "WHERE id = ?")
				.set(nome)
				.set(documentos().cpf())
				.set(documentos().rg())
				.set(documentos().sexo())
				.set(documentos().tratamento())
				.set(documentos().endereco().logradouro())
				.set(documentos().endereco().numero())
				.set(documentos().endereco().complemento())
				.set(documentos().endereco().bairro())
				.set(documentos().endereco().cidade())
				.set(documentos().endereco().cep())
				.set(id)
				.execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
