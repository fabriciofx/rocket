package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.Pessoa;
import com.github.fabriciofx.rocket.dominio.bd.BdPessoa;
import com.github.fabriciofx.rocket.dominio.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.doc.Nome;
import com.github.fabriciofx.rocket.dominio.doc.Rg;
import com.github.fabriciofx.rocket.dominio.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.doc.Tratamento;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Estado;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.simples.SimplesDocumentos;
import com.github.fabriciofx.rocket.dominio.simples.SimplesEndereco;
import com.github.fabriciofx.rocket.dominio.simples.SimplesFone;
import com.github.fabriciofx.rocket.dominio.simples.SimplesFones;
import com.github.fabriciofx.rocket.dominio.simples.SimplesPessoa;
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
		final Pessoa pessoa = new BdPessoa(
			ds,
			new UuidId()
		).salva(
			new SimplesPessoa(
				new Nome("Jason Bourne"),
				new SimplesDocumentos(
					new Cpf("57381117533"),
					new Rg("62527362"),
					Sexo.MASCULINO,
					Tratamento.SENHOR,
					new SimplesEndereco(
						new Logradouro("Av Gov Torquato Nepomuceno Neves"),
						new Numero("123"),
						new Complemento("AP 101"),
						new Bairro("Vila Madalena"),
						new Cidade("São Paulo", Estado.SP),
						new Cep("48035120")
					),
					new SimplesFones(
						new SimplesFone("81988144321"),
						new SimplesFone("83999231234")
					)
				)
			)
		);
		System.out.println(new XmlFormat(
			pessoa.print(
				new XmlMedia("pessoa")).toString()
			).toString()
		);
	}
}
