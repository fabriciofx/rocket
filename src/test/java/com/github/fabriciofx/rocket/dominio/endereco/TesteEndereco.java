package com.github.fabriciofx.rocket.dominio.endereco;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fabriciofx.rocket.infra.media.XmlMedia;
import com.jcabi.jdbc.JdbcSession;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class TesteEndereco {
	private static DataSource ds() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final String url = String.format("jdbc:h2:%s%s%s", path.toString(),
				File.separator, "endereco");
		final HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername("sa");
		config.setPassword("");
		return new HikariDataSource(config);
	}
	
	@Before
	public void init() throws IOException {
		try {
			new JdbcSession(ds()).sql("CREATE TABLE IF NOT EXISTS " +
				"endereco(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
				"logradouro VARCHAR(255), numero VARCHAR(255), " +
				"complemento VARCHAR(255), bairro VARCHAR(255), " +
				"cidade VARCHAR(255), cep VARCHAR(255))"	
				).execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}
	
	@After
	public void finaliza() throws IOException {
		try {
			new JdbcSession(ds()).sql("DROP TABLE IF EXISTS endereco").execute();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}	
	
	@Test
	public void xml() throws IOException {
		final String LS = System.lineSeparator();
		final Enderecos enderecos = new SqlEnderecos(ds());
		final Endereco endereco = enderecos.endereco(
			new Logradouro("Av Gov Torquato Nepomuceno Neves"),
			new Numero("123"),
			new Complemento("AP 101"),
			new Bairro("Vila Madalena"),
			new Cidade("São Paulo", Estado.SP),
			new Cep("48035120")
		);
		final String xml =
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"+ LS
			+ "<endereco>" + LS
			+ "<logradouro>Av Gov Torquato Nepomuceno Neves</logradouro>" + LS
			+ "<numero>123</numero>" + LS
			+ "<complemento>AP 101</complemento>" + LS
			+ "<bairro>Vila Madalena</bairro>" + LS
			+ "<cidade>São Paulo-SP</cidade>"+ LS
			+ "<cep>48035120</cep>" + LS
			+ "<id>1</id>" + LS
			+ "</endereco>" + LS;
		assertEquals(xml, endereco.print(new XmlMedia("endereco")).toString());
	}
}
