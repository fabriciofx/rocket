package com.github.fabriciofx.rocket.dominio.pessoa;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.doc.Nome;
import com.github.fabriciofx.rocket.dominio.doc.Rg;
import com.github.fabriciofx.rocket.dominio.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.doc.Tratamento;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Estado;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.repositorio.NumId;
import com.github.fabriciofx.rocket.ds.TestDataSource;
import com.github.fabriciofx.rocket.media.XmlMedia;
import com.jcabi.jdbc.JdbcSession;

public final class TestePessoa {	
	@Before
	public void init() throws IOException {
		try {
			final DataSource ds = new TestDataSource("pessoa").dataSource();
			new JdbcSession(ds).sql(
					"CREATE TABLE IF NOT EXISTS pessoa ("
					+ "id BIGINT PRIMARY KEY AUTO_INCREMENT,"
					+ "nome VARCHAR(100),"
					+ "sexo VARCHAR(20),"
					+ "tratamento VARCHAR(20),"
					+ "cpf VARCHAR(20),"
					+ "rg VARCHAR(50),"
					+ "logradouro VARCHAR(100),"
					+ "numero VARCHAR(10),"
					+ "complemento VARCHAR(100),"
					+ "bairro VARCHAR(100),"
					+ "cidade VARCHAR(50),"
					+ "estado VARCHAR(2), "
					+ "cep VARCHAR(9))"
			).execute();
			new JdbcSession(ds).sql(
					"CREATE TABLE IF NOT EXISTS fone ("
					+ "pessoa BIGINT PRIMARY KEY,"
					+ "numero VARCHAR(20),"
					+ "tipo VARCHAR(10),"
					+ "operadora VARCHAR(15),"
					+ "FOREIGN KEY (pessoa) REFERENCES pessoa(id))"
			).execute();			
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
	
	@After
	public void finaliza() throws IOException {
		try {
			final DataSource ds = new TestDataSource("pessoa").dataSource();			
			new JdbcSession(ds).sql("DROP TABLE IF EXISTS fone").execute();
			new JdbcSession(ds).sql("DROP TABLE IF EXISTS pessoa").execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
	
	@Test
	public void xml() throws IOException {
		final DataSource ds = new TestDataSource("pessoa").dataSource();
		final String LS = System.lineSeparator();
		final Pessoa pessoa = new SqlPessoa(
			new SimplesPessoa(
				new Nome("José de Alencar"),
				Sexo.MASCULINO,
				Tratamento.SENHOR,
				new Cpf("60840226772"),
				new Rg("12345678"),
				new Endereco(
					new Logradouro("Av Gov Torquato Nepomuceno Neves"),
					new Numero("123"),
					new Complemento("AP 101"),
					new Bairro("Vila Madalena"),
					new Cidade("São Paulo", Estado.SP),
					new Cep("48035120")
				)			
			),
			new NumId(1L)
		);
		pessoa.save(ds);
		final String xml = 
			"<pessoa>" + LS
			+ "<id>1</id>" + LS
			+ "<nome>José de Alencar</nome>" + LS
			+ "<sexo>MASCULINO</sexo>" + LS
			+ "<tratamento>SENHOR</tratamento>" + LS
			+ "<cpf>60840226772</cpf>" + LS
			+ "<rg>12345678 SSP-PB</rg>" + LS
			+ "<logradouro>Av Gov Torquato Nepomuceno Neves</logradouro>" + LS
			+ "<numero>123</numero>" + LS
			+ "<complemento>AP 101</complemento>" + LS
			+ "<bairro>Vila Madalena</bairro>" + LS
			+ "<cidade>São Paulo</cidade>" + LS
			+ "<estado>SP</estado>" + LS
			+ "<cep>48035120</cep>" + LS
			+ "</pessoa>" + LS;
		assertEquals(xml, pessoa.find(ds, new NumId(1L))
				.print(new XmlMedia("pessoa")).toString());
	}
}
