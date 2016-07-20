package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.Fones;
import com.github.fabriciofx.rocket.dominio.Pessoa;
import com.github.fabriciofx.rocket.dominio.Pessoas;
import com.github.fabriciofx.rocket.dominio.sql.SqlPessoas;
import com.github.fabriciofx.rocket.ds.TestDataSource;
import com.github.fabriciofx.rocket.media.XmlMedia;
import com.jcabi.jdbc.JdbcSession;

public final class TestePessoa {
	final DataSource ds = new TestDataSource("testebd").dataSource();

	@Before
	public void init() throws IOException {
		try {
			final JdbcSession session = new JdbcSession(ds);
			session.sql("CREATE TABLE IF NOT EXISTS pessoa ("
				+ "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
				+ "nome VARCHAR(100) NOT NULL)"
			).execute();
			session.sql(
				"CREATE TABLE IF NOT EXISTS fone ("
				+ "pessoa BIGINT NOT NULL,"
				+ "numero VARCHAR(20) NOT NULL,"
				+ "FOREIGN KEY(pessoa) REFERENCES pessoa(id),"
				+ "PRIMARY KEY(pessoa, numero))"
			).execute();						
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@After
	public void finaliza() throws IOException {
		try {
			final JdbcSession session = new JdbcSession(ds);
			session.sql("DROP TABLE IF EXISTS fone").execute();
			session.sql("DROP TABLE IF EXISTS pessoa").execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Test
	public void pessoa() throws IOException {
		final Pessoas pessoas = new SqlPessoas(ds);
		final Pessoa pessoa = pessoas.salva("Jason Bourne");
		final Fones fones = pessoa.fones();
		fones.salva("83999231234");
		final Fone fone = fones.fone(1L);
		System.out.println(pessoa.print(new XmlMedia("pessoa")).toString());
		System.out.println(fone.print(new XmlMedia("fone")).toString());
	}
}
