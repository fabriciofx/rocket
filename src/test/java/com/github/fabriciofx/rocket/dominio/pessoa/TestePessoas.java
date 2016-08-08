package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.H2Database;
import com.github.fabriciofx.rocket.db.SqlScript;
import com.github.fabriciofx.rocket.db.TestDatabase;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.endereco.SimplesEndereco;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Estado;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Numero;
import com.github.fabriciofx.rocket.dominio.fone.Fone.Operadora;
import com.github.fabriciofx.rocket.dominio.fone.Fone.Tipo;
import com.github.fabriciofx.rocket.dominio.fone.SimplesFone;
import com.github.fabriciofx.rocket.dominio.fone.SimplesFones;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.SimplesDocumentos;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Rg;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Tratamento;
import com.github.fabriciofx.rocket.media.XmlFormat;
import com.github.fabriciofx.rocket.media.XmlMedia;
import com.github.fabriciofx.rocket.misc.ResourcePath;

public final class TestePessoas {
	@Test
	public void pessoa() throws IOException {
		final Database testebd = new TestDatabase(
			new H2Database("testebd")
		);
		testebd.exec(
			new SqlScript(
				new ResourcePath(
					"db",
					"teste-pessoa-db-init.sql"
				)
			)
		);
		final Pessoas<SqlPessoa> pessoas = new SqlPessoas(testebd.dataSource());
		pessoas.pessoa(
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
					new SimplesFone("81988144321", Tipo.CELULAR, Operadora.OI),
					new SimplesFone("83999231234", Tipo.CELULAR, Operadora.TIM)
				)
			)
		);		
		for (final Pessoa p : pessoas.todas()) {
			System.out.println(new XmlFormat(
				p.print(
					new XmlMedia("pessoa")).toString()
				).toString()
			);			
		}
		testebd.exec(
			new SqlScript(
				new ResourcePath(
					TestePessoas.class,
					"db",
					"teste-pessoa-db-destroy.sql"
				)
			)
		);
	}
	
	@Test
	public void alteraNome() throws IOException {
		final Database testebd = new TestDatabase(
				new H2Database("testebd")
		);
		testebd.exec(
			new SqlScript(
				new ResourcePath(
					"db",
					"teste-pessoa-db-init.sql"
				)
			)
		);
		final Pessoas<SqlPessoa> pessoas = new SqlPessoas(testebd.dataSource());
		final SqlPessoa jason = pessoas.pessoa(
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
					new SimplesFone("81988144321", Tipo.CELULAR, Operadora.OI),
					new SimplesFone("83999231234", Tipo.CELULAR, Operadora.TIM)
				)
			)
		);
		System.out.println(new XmlFormat(
				jason.print(
					new XmlMedia("pessoa")).toString()
				).toString()
			);			
		jason.atualiza(new Nome("Jason M. Bourne"), jason.documentos());
		System.out.println(new XmlFormat(
				jason.print(
					new XmlMedia("pessoa")).toString()
				).toString()
			);			
		testebd.exec(
			new SqlScript(
				new ResourcePath(
					TestePessoas.class,
					"db",
					"teste-pessoa-db-destroy.sql"
				)
			)
		);
	}
}
