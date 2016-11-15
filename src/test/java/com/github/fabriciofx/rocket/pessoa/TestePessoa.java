package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.SqlScript;
import com.github.fabriciofx.rocket.db.test.TestDatabase;
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
		final String endereco =
			"Av Gov Torquato Nepomuceno Neves, 123 - AP 101\n" +
			"Vila Madalena - São Paulo-SP\n" +
			"48035120";
		final Pessoas pessoas = new SqlPessoas(testebd);
		final Pessoa pessoa = pessoas.pessoa(
			"Jason Bourne", endereco, "81 98814-4321"
		);
		MatcherAssert.assertThat(
			XhtmlMatchers.xhtml(
				new XmlPessoa(pessoa)
			),
			XhtmlMatchers.hasXPaths(
				"/pessoa/id",
				"/pessoa/nome[text()='Jason Bourne']",
				"/pessoa/endereco[text()='" + endereco + "']",
				"/pessoa/fone[text()='81 98814-4321']"
			)
		);
	}
}
