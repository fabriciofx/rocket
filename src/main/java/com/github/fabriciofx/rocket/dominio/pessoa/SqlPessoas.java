package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.fone.Fone;
import com.github.fabriciofx.rocket.dominio.fone.Fones;
import com.github.fabriciofx.rocket.dominio.fone.SqlFones;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.Documentos;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.NumId;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoas implements Pessoas<SqlPessoa> {
	private final transient Database db;

	public SqlPessoas(final Database db) {
		this.db = db;
	}
	
	@Override
	public SqlPessoa pessoa(final Nome nome, final Documentos documentos)
			throws IOException {
		try {
			final Id id = new NumId(
				new JdbcSession(db.source())
					.sql("INSERT INTO pessoa (nome, cpf, rg, sexo, tratamento, "
						+ "logradouro, numero, complemento, bairro, cidade, "
						+ "cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
					.set(nome)
					.set(documentos.cpf())
					.set(documentos.rg())
					.set(documentos.sexo())
					.set(documentos.tratamento())
					.set(documentos.endereco().logradouro())
					.set(documentos.endereco().numero())
					.set(documentos.endereco().complemento())
					.set(documentos.endereco().bairro())
					.set(documentos.endereco().cidade())
					.set(documentos.endereco().cep())
					.insert(SingleOutcome.LAST_INSERT_ID)
				);
			final Fones fones = new SqlFones(db, id);
			for (final Fone f : documentos.fones().todos()) {
				fones.salva(f.numero(), f.tipo(), f.operadora());	
			}
			return new SqlPessoa(db, id);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public SqlPessoa pessoa(final Id id) throws IOException {
		return new SqlPessoa(db, id);
	}

	@Override
	public List<SqlPessoa> todas() throws IOException {
		try {
			return new JdbcSession(db.source())
				.sql("SELECT id FROM pessoa")
				.select(new ListOutcome<SqlPessoa>(new PessoaMapping()));
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}
	
	private class PessoaMapping implements ListOutcome.Mapping<SqlPessoa> {
		@Override
		public SqlPessoa map(final ResultSet rs) throws SQLException {
			return new SqlPessoa(db, new NumId(rs.getLong(1)));
		}		
	}
}
