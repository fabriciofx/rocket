package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.SqlScript;
import com.github.fabriciofx.rocket.db.test.TestDatabase;
import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.media.XmlFormat;
import com.github.fabriciofx.rocket.misc.ResourcePath;
import com.jcabi.matchers.XhtmlMatchers;

public final class TestePessoaFisica {
	@Test
	public void pessoaFisica() throws IOException {
		final Database testebd = new TestDatabase("testebd");
		testebd.exec(
			new SqlScript(
				new ResourcePath(
					"db",
					"teste-pessoa-db-init.sql"
				)
			)
		);
		final String endereco =
			"Av Gov Torquato Nepomuceno Neves, 123 - AP 101\n" +
			"Vila Madalena - São Paulo-SP\n" +
			"48035120";
		final PessoasFisicas pessoasFisicas = new SqlPessoasFisicas(testebd);
		final PessoaFisica pessoaFisica = pessoasFisicas.pessoaFisica(
			new Nome("Jason Bourne"),
			endereco,
			"81 98814-4321",
			"57381117533",
			"62527362 SSP-PB"
		);
		System.out.println(
			new XmlFormat(
				new XmlPessoa(pessoaFisica).toString()
			)
		);
		MatcherAssert.assertThat(
			XhtmlMatchers.xhtml(
				new XmlPessoa(pessoaFisica)
			),
			XhtmlMatchers.hasXPaths(
				"/pessoa/id",
				"/pessoa/nome[text()='Jason Bourne']",
				"/pessoa/endereco[text()='" + endereco + "']",
				"/pessoa/fone[text()='81 98814-4321']",
				"/pessoa/cpf[text()='57381117533']",
				"/pessoa/rg[text()='62527362 SSP-PB']"				
			)
		);
	}
}
