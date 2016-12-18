package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.util.HashMap;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.SqlScript;
import com.github.fabriciofx.rocket.db.h2.H2Database;
import com.github.fabriciofx.rocket.db.test.TestDatabase;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.XmlMedia;
import com.github.fabriciofx.rocket.misc.ResourcePath;
import com.jcabi.matchers.XhtmlMatchers;

public final class TestePessoa {
	@Test
	public void pessoa() throws IOException {
		final Database testebd = new TestDatabase("testebd");
		testebd.exec(
			new SqlScript(
				new ResourcePath(
					"db",
					"teste-pessoa-db-init.sql"
				)
			)
		);
		final Pessoas pessoas = new PessoasSql(testebd);
		final Pessoa pessoa = pessoas.pessoa(
			"Jason Bourne",
			new HashMap<String, String>() {{
				put("tratamento", "Sr.");
				put("cpf", "11122233345");
				put("rg", "1234567 SSP-PB");
				put("sexo", "M");
				put("logradouro", "Av Gov Torquato Nepomuceno Neves");
				put("numero", "123");
				put("complemento", "AP-101");
				put("bairro", "Vila Madalena");
				put("cidade", "São Paulo-SP");
				put("cep", "4803512");
				put("email", "jason.bourne@cia.gov.us");
			}}
		);
		MatcherAssert.assertThat(
			XhtmlMatchers.xhtml(
				new PessoaXml(pessoa)
			),
			XhtmlMatchers.hasXPaths(
				"/pessoa/id",
				"/pessoa/nome[text()='Jason Bourne']",
				"/pessoa/tratamento[text()='Sr.']",
				"/pessoa/cpf[text()='11122233345']",
				"/pessoa/sexo[text()='M']",
				"/pessoa/rg[text()='1234567 SSP-PB']",
				"/pessoa/logradouro[text()='Av Gov Torquato Nepomuceno Neves']",
				"/pessoa/numero[text()='123']",
				"/pessoa/complemento[text()='AP-101']",
				"/pessoa/bairro[text()='Vila Madalena']",
				"/pessoa/cidade[text()='São Paulo-SP']",
				"/pessoa/cep[text()='4803512']",
				"/pessoa/email[text()='jason.bourne@cia.gov.us']"
			)
		);
	}
	
	@Test
	public void renomeia() throws IOException {
		final Database testebd = new TestDatabase("testebd");
		testebd.exec(
			new SqlScript(
				new ResourcePath(
					"db",
					"teste-pessoa-db-init.sql"
				)
			)
		);
		final Pessoas pessoas = new PessoasSql(testebd);
		final Pessoa pessoa = pessoas.pessoa(
			"Jason Bourne",
			new HashMap<String, String>() {{
				put("tratamento", "Sr.");
				put("cpf", "11122233345");
				put("rg", "1234567 SSP-PB");
				put("sexo", "M");
				put("logradouro", "Av Gov Torquato Nepomuceno Neves");
				put("numero", "123");
				put("complemento", "AP-101");
				put("bairro", "Vila Madalena");
				put("cidade", "São Paulo-SP");
				put("cep", "4803512");
				put("email", "jason.bourne@cia.gov.us");
			}}
		);
		pessoa.renomeia("David Webb");
		MatcherAssert.assertThat(
			XhtmlMatchers.xhtml(
				new PessoaXml(pessoa)
			),
			XhtmlMatchers.hasXPaths(
				"/pessoa/id",
				"/pessoa/nome[text()='David Webb']",
				"/pessoa/tratamento[text()='Sr.']",
				"/pessoa/cpf[text()='11122233345']",
				"/pessoa/sexo[text()='M']",
				"/pessoa/rg[text()='1234567 SSP-PB']",
				"/pessoa/logradouro[text()='Av Gov Torquato Nepomuceno Neves']",
				"/pessoa/numero[text()='123']",
				"/pessoa/complemento[text()='AP-101']",
				"/pessoa/bairro[text()='Vila Madalena']",
				"/pessoa/cidade[text()='São Paulo-SP']",
				"/pessoa/cep[text()='4803512']",
				"/pessoa/email[text()='jason.bourne@cia.gov.us']"
			)
		);
	}
	
	@Test
	public void desempenho() throws IOException {
		final Database testebd = new H2Database("testebd");
		testebd.exec(
			new SqlScript(
				new ResourcePath(
					"db",
					"teste-pessoa-db-init.sql"
				)
			)
		);
		final Pessoas pessoas = new PessoasSql(testebd);
		final Pessoa pessoa = new PessoaCached(pessoas.pessoa(
			"Jason Bourne",
			new HashMap<String, String>() {{
				put("tratamento", "Sr.");
				put("cpf", "11122233345");
				put("rg", "1234567 SSP-PB");
				put("sexo", "M");
				put("logradouro", "Av Gov Torquato Nepomuceno Neves");
				put("numero", "123");
				put("complemento", "AP-101");
				put("bairro", "Vila Madalena");
				put("cidade", "São Paulo-SP");
				put("cep", "4803512");
				put("email", "jason.bourne@cia.gov.us");
			}}
		));
		final Media media = new XmlMedia("pessoa");
		final long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			pessoa.about(media);
		}
		final long end = System.currentTimeMillis();
		System.out.println("Total time: " + (end - start) + " ms");
	}
}
