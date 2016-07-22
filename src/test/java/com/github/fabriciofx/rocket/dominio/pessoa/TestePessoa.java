package com.github.fabriciofx.rocket.dominio.pessoa;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.Pessoa;
import com.github.fabriciofx.rocket.dominio.Pessoas;
import com.github.fabriciofx.rocket.dominio.sql.SqlPessoa;
import com.github.fabriciofx.rocket.dominio.sql.SqlPessoas;
import com.github.fabriciofx.rocket.ds.TestDataSource;
import com.github.fabriciofx.rocket.id.UuidId;
import com.github.fabriciofx.rocket.media.XmlFormat;
import com.github.fabriciofx.rocket.media.XmlMedia;
import com.jcabi.jdbc.JdbcSession;

public final class TestePessoa {
	final DataSource ds = new TestDataSource("testebd").dataSource();

	@Before
	public void init() throws IOException {
		try {
			final JdbcSession session = new JdbcSession(ds);
			session.sql("CREATE TABLE IF NOT EXISTS pessoa ("
				+ "id VARCHAR(36) PRIMARY KEY,"
				+ "nome VARCHAR(100) NOT NULL)"
			).execute();
			session.sql(
				"CREATE TABLE IF NOT EXISTS fone ("
				+ "pessoa VARCHAR(36) NOT NULL,"
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
		final Pessoa pessoa = new SqlPessoa(
			ds,
			new UuidId("09F22AF7-727A-4FEE-9138-BABAC0E48ED3")
		).salva("Jason Bourne",
			Arrays.asList(
				"83999231234",
				"81988144321"
			)
		);
		final String ls = System.lineSeparator();
		final String xml =
			"<pessoa>" + ls +
			"  <id>09F22AF7-727A-4FEE-9138-BABAC0E48ED3</id>" + ls +
			"  <nome>Jason Bourne</nome>" + ls +
			"  <fone>81988144321</fone>" + ls +
			"  <fone>83999231234</fone>" + ls +
			"</pessoa>" + ls;
		assertEquals(xml, new XmlFormat(
			pessoa.print(
				new XmlMedia("pessoa")).toString()
			).toString()
		);
	}
	
	@Test
	public void deletaFone() throws IOException {
		final Pessoa pessoa = new SqlPessoa(
			ds,
			new UuidId("09F22AF7-727A-4FEE-9138-BABAC0E48ED3")
		).salva("Jason Bourne",
			Arrays.asList(
				"83999231234",
				"81988144321"
			)
		);
		pessoa.fones().fone("81988144321").apaga();
		final String ls = System.lineSeparator();
		final String xml =
			"<pessoa>" + ls +
			"  <id>09F22AF7-727A-4FEE-9138-BABAC0E48ED3</id>" + ls +
			"  <nome>Jason Bourne</nome>" + ls +
			"  <fone>83999231234</fone>" + ls +
			"</pessoa>" + ls;
		assertEquals(xml, new XmlFormat(
			pessoa.print(
				new XmlMedia("pessoa")).toString()
			).toString()
		);
	}

	@Test
	public void pessoas() throws IOException {
		final Pessoas pessoas = new SqlPessoas(ds);
		new SqlPessoa(
			ds,
			new UuidId("09F22AF7-727A-4FEE-9138-BABAC0E48ED3")
		).salva("Jason Bourne",
			Arrays.asList(
				"83999231234",
				"81988144321"
			)
		);		
		new SqlPessoa(
			ds,
			new UuidId("09F22AF7-727A-4FEE-9138-BABAC0E48ED4")
		).salva("Ana Jetson",
			Arrays.asList(
				"21955236789",
				"11974562345"
			)
		);
		for (final Pessoa p : pessoas.todas()) {
			System.out.println(new XmlFormat(
				p.print(new XmlMedia("pessoa")).toString()).toString()
			);
		}
	}
}
