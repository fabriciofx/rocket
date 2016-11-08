package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.SqlScript;
import com.github.fabriciofx.rocket.db.test.TestDatabase;
import com.github.fabriciofx.rocket.doc.Cpf;
import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.doc.Rg;
import com.github.fabriciofx.rocket.doc.Sexo;
import com.github.fabriciofx.rocket.doc.Tratamento;
import com.github.fabriciofx.rocket.doc.Fone.Operadora;
import com.github.fabriciofx.rocket.doc.Fone.Tipo;
import com.github.fabriciofx.rocket.doc.endereco.Bairro;
import com.github.fabriciofx.rocket.doc.endereco.Cep;
import com.github.fabriciofx.rocket.doc.endereco.Cidade;
import com.github.fabriciofx.rocket.doc.endereco.Complemento;
import com.github.fabriciofx.rocket.doc.endereco.Estado;
import com.github.fabriciofx.rocket.doc.endereco.Logradouro;
import com.github.fabriciofx.rocket.doc.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.endereco.ConstEndereco;
import com.github.fabriciofx.rocket.dominio.fone.ConstFone;
import com.github.fabriciofx.rocket.dominio.fone.ConstFones;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.ConstDocumentos;
import com.github.fabriciofx.rocket.misc.ResourcePath;
import com.jcabi.matchers.XhtmlMatchers;

public final class TestePessoas {
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
		final Pessoas<SqlPessoa> pessoas = new SqlPessoas(testebd);
		final Pessoa pessoa = pessoas.pessoa(
			new Nome("Jason Bourne"),
			new ConstDocumentos(
				new Cpf("57381117533"),
				new Rg("62527362"),
				Sexo.MASCULINO,
				Tratamento.SENHOR,
				new ConstEndereco(
					new Logradouro("Av Gov Torquato Nepomuceno Neves"),
					new Numero("123"),
					new Complemento("AP 101"),
					new Bairro("Vila Madalena"),
					new Cidade("São Paulo", Estado.SP),
					new Cep("48035120")
				)
			),
			new ConstFones(
				new ConstFone("81988144321", Tipo.CELULAR, Operadora.OI),
				new ConstFone("83999231234", Tipo.CELULAR, Operadora.TIM)
			)
		);
		MatcherAssert.assertThat(
			XhtmlMatchers.xhtml(
				new XmlPessoa(pessoa)
			),
			XhtmlMatchers.hasXPaths(
				"/pessoa/id",
				"/pessoa/nome[text()='Jason Bourne']",
				"/pessoa/cpf[text()='57381117533']",
				"/pessoa/rg[text()='62527362 SSP-PB']",
				"/pessoa/sexo[text()='MASCULINO']",
				"/pessoa/tratamento[text()='SENHOR']",
				"/pessoa/logradouro[text()='Av Gov Torquato Nepomuceno Neves']",
				"/pessoa/numero[text()='123']",
				"/pessoa/complemento[text()='AP 101']",
				"/pessoa/bairro[text()='Vila Madalena']",
				"/pessoa/cidade[text()='São Paulo-SP']",
				"/pessoa/cep[text()='48035120']",
				"/pessoa/numero[text()='81988144321']",
				"/pessoa/tipo[text()='CELULAR']",
				"/pessoa/operadora[text()='OI']",
				"/pessoa/numero[text()='83999231234']",
				"/pessoa/tipo[text()='CELULAR']",
				"/pessoa/operadora[text()='TIM']"
			)
		);
	}
	
	@Test
	public void alteraNome() throws IOException {
		final Database testebd = new TestDatabase("testebd");
		testebd.exec(
			new SqlScript(
				new ResourcePath(
					"db",
					"teste-pessoa-db-init.sql"
				)
			)
		);
		final Pessoas<SqlPessoa> pessoas = new SqlPessoas(testebd);
		final SqlPessoa pessoa = pessoas.pessoa(
			new Nome("Jason Bourne"),
			new ConstDocumentos(
				new Cpf("57381117533"),
				new Rg("62527362"),
				Sexo.MASCULINO,
				Tratamento.SENHOR,
				new ConstEndereco(
					new Logradouro("Av Gov Torquato Nepomuceno Neves"),
					new Numero("123"),
					new Complemento("AP 101"),
					new Bairro("Vila Madalena"),
					new Cidade("São Paulo", Estado.SP),
					new Cep("48035120")
				)
			),
			new ConstFones(
				new ConstFone("81988144321", Tipo.CELULAR, Operadora.OI),
				new ConstFone("83999231234", Tipo.CELULAR, Operadora.TIM)
			)
		);
		pessoa.atualiza(new Nome("Jason M. Bourne"), pessoa.documentos());		
		MatcherAssert.assertThat(
			XhtmlMatchers.xhtml(
				new XmlPessoa(pessoa)
			),
			XhtmlMatchers.hasXPaths(
				"/pessoa/id",
				"/pessoa/nome[text()='Jason M. Bourne']",
				"/pessoa/cpf[text()='57381117533']",
				"/pessoa/rg[text()='62527362 SSP-PB']",
				"/pessoa/sexo[text()='MASCULINO']",
				"/pessoa/tratamento[text()='SENHOR']",
				"/pessoa/logradouro[text()='Av Gov Torquato Nepomuceno Neves']",
				"/pessoa/numero[text()='123']",
				"/pessoa/complemento[text()='AP 101']",
				"/pessoa/bairro[text()='Vila Madalena']",
				"/pessoa/cidade[text()='São Paulo-SP']",
				"/pessoa/cep[text()='48035120']",
				"/pessoa/numero[text()='81988144321']",
				"/pessoa/tipo[text()='CELULAR']",
				"/pessoa/operadora[text()='OI']",
				"/pessoa/numero[text()='83999231234']",
				"/pessoa/tipo[text()='CELULAR']",
				"/pessoa/operadora[text()='TIM']"
			)
		);		
	}
}
