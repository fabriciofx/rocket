package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.Url;
import com.github.fabriciofx.rocket.system.NamedUser;
import com.jcabi.jdbc.JdbcSession;

public final class TestePessoaDatabase implements Database {
	private final transient Database db;
	
	public TestePessoaDatabase(final Database db) {
		this.db = db;
	}
	
	@Override
	public NamedUser user() {
		return db.user();
	}

	@Override
	public Url url() {
		return db.url();
	}

	@Override
	public DataSource dataSource() throws IOException {
		return db.dataSource();
	}
	
	public TestePessoaDatabase init() throws IOException {
		try {
			final JdbcSession session = new JdbcSession(db.dataSource());
			session.sql(
				"CREATE TABLE IF NOT EXISTS pessoa ("
				+ "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
				+ "nome VARCHAR(100) NOT NULL,"
				+ "cpf VARCHAR(11) NOT NULL,"
				+ "rg VARCHAR(20) NOT NULL,"
				+ "sexo VARCHAR(9) NOT NULL,"
				+ "tratamento VARCHAR(10) NOT NULL,"
				+ "logradouro VARCHAR(100) NOT NULL,"
				+ "numero VARCHAR(5) NOT NULL,"
				+ "complemento VARCHAR(100) NOT NULL,"
				+ "bairro VARCHAR(50) NOT NULL,"
				+ "cidade VARCHAR(50) NOT NULL,"
				+ "cep VARCHAR(8) NOT NULL)"
			).execute();
			session.sql(
				"CREATE TABLE IF NOT EXISTS fone ("
				+ "id BIGINT NOT NULL,"
				+ "numero VARCHAR(20) NOT NULL,"
				+ "tipo VARCHAR(20) NOT NULL,"
				+ "operadora VARCHAR(20) NOT NULL,"
				+ "FOREIGN KEY(id) REFERENCES pessoa(id),"
				+ "PRIMARY KEY(id, numero))"
			).execute();						
		} catch (final SQLException e) {
			throw new IOException(e);
		}
		return new TestePessoaDatabase(db);
	}

	public void finaliza() throws IOException {
		try {
			final JdbcSession session = new JdbcSession(db.dataSource());
			session.sql("DROP TABLE IF EXISTS fone").execute();
			session.sql("DROP TABLE IF EXISTS pessoa").execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
}
