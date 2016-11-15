package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.doc.fone.Fones;
import com.github.fabriciofx.rocket.doc.fone.SqlFones;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlPessoa implements Pessoa {
	private final Database db;
	private final Id id;

	public SqlPessoa(final Database db, final Id id) {
		this.db = db;
		this.id = id;
	}

	@Override
	public Media about(final Media media) throws IOException {
		try {
			final Map<String, String> documentos = new JdbcSession(db.source())
				.sql("SELECT nome, cpf, rg, sexo, tratamento, logradouro, " +
					"numero, complemento, bairro, cidade, cep FROM pessoa " +
					"WHERE id = ?")
				.set(id)
				.select(
					new ListOutcome<Map<String, String>>(
						new DocumentosMapping()
					)
				)
				.get(0);
			return media
				.with("id", id)
				.with("nome", documentos.get("nome"))
				.with("cpf", documentos.get("cpf"))
				.with("rg", documentos.get("rg"))
				.with("sexo",documentos.get("sexo"))				
				.with("tratamento", documentos.get("tratamento"))
				.with("logradouro", documentos.get("logradouro"))
				.with("numero", documentos.get("numero"))
				.with("complemento", documentos.get("complemento"))
				.with("bairro", documentos.get("bairro"))
				.with("cidade", documentos.get("cidade"))
				.with("cep", documentos.get("cep"));
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}

	private final class DocumentosMapping implements
		ListOutcome.Mapping<Map<String, String>> {
		@Override
		public Map<String, String> map(final ResultSet rs) throws SQLException {
			final ResultSetMetaData rsmd = rs.getMetaData();
			final int colunas = rsmd.getColumnCount();
			final HashMap<String, String> map = new HashMap<>();
			for (int i = 1; i <= colunas; i++) {
				map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(i));
			}
			return map;
		}
	}	
	
	@Override
	public Fones fones() throws IOException {
		return new SqlFones(db, id);
	}

	@Override
	public void atualize(final Map<String, String> documentos,
		final Fones fones) throws IOException {
		try {
			new JdbcSession(db.source())
				.sql("UPDATE pessoa SET nome = ?, cpf = ?, rg = ?, " +
					"sexo = ?, tratamento = ?, logradouro = ?, numero = ?, " +
					"complemento = ?, bairro = ?, cidade = ?, cep = ? " +
					"WHERE id = ?")
				.set(documentos.get("nome"))
				.set(documentos.get("cpf"))
				.set(documentos.get("rg"))
				.set(documentos.get("sexo"))				
				.set(documentos.get("tratamento"))
				.set(documentos.get("logradouro"))
				.set(documentos.get("numero"))
				.set(documentos.get("complemento"))
				.set(documentos.get("bairro"))
				.set(documentos.get("cidade"))
				.set(documentos.get("cep"))
				.set(id)
				.execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
